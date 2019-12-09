package com.tuya.smart.android.demo.device.standard;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuya.smart.android.common.utils.L;
import com.tuya.smart.android.demo.R;
import com.tuya.smart.android.demo.base.bean.AlertPickBean;
import com.tuya.smart.android.demo.base.utils.DialogUtil;
import com.tuya.smart.android.demo.base.utils.ProgressUtil;
import com.tuya.smart.android.demo.base.utils.SchemaMapper;
import com.tuya.smart.android.demo.base.utils.ToastUtil;
import com.tuya.smart.android.demo.base.widget.AlertPickDialog;
import com.tuya.smart.android.demo.device.DpLogBean;
import com.tuya.smart.android.demo.device.ICommonDeviceDebugView;
import com.tuya.smart.android.demo.device.StandardSchemaBean;
import com.tuya.smart.android.device.bean.EnumSchemaBean;
import com.tuya.smart.android.device.bean.SchemaBean;
import com.tuya.smart.android.device.enums.ModeEnum;
import com.tuya.smart.android.mvp.presenter.BasePresenter;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.IDevListener;
import com.tuya.smart.sdk.api.IDeviceListener;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.StandSchema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by letian on 16/8/28.
 */

public class StandardCommonDeviceDebugPresenter extends BasePresenter implements IDeviceListener {

    private static final String TAG = "CommonDeviceDebugPresenter";
    private Context mContext;
    private ICommonDeviceDebugView mView;
    public static final String INTENT_DEVID = "intent_devId";
    private String mDevId;
    private DeviceBean mDevBean;
    private ITuyaDevice mTuyaDevice;
    private LogCountDownLatch mDownLatch;

    public StandardCommonDeviceDebugPresenter(Context context, ICommonDeviceDebugView view) {
        mContext = context;
        mView = view;
        initData();
        initListener();
    }

    private void initListener() {
        mTuyaDevice = TuyaHomeSdk.newDeviceInstance(mDevId);
        mTuyaDevice.registerDeviceListener(this);
    }

    private void initData() {
        mDevId = ((Activity) mContext).getIntent().getStringExtra(INTENT_DEVID);
        mDevBean = TuyaHomeSdk.getDataInstance().getDeviceBean(mDevId);
        if (mDevBean == null) {
            ((Activity) mContext).finish();
        }
    }

    public String getTitle() {
        return mDevBean == null ? "" : mDevBean.getName();
    }

    public DeviceBean getDevBean() {
        return mDevBean;
    }

    public List<StandardSchemaBean> getSchemaList() {
        if (mDevBean == null) return new ArrayList<>();

        List<StandSchema.FunctionSchemaListBean> functionSchemaListBeans = mDevBean.getProductBean().getsSchema().getFunctionSchemaList();
//        List<StandSchema.StatusSchemaListBean> statusSchemaListBeans = mDevBean.getProductBean().getsSchema().getStatusSchemaList();

        List<StandardSchemaBean> schemaBeanArrayList = new ArrayList<>();

        if (functionSchemaListBeans != null && functionSchemaListBeans.size() > 0) {
            for (StandSchema.FunctionSchemaListBean functionSchemaListBean : functionSchemaListBeans) {
                StandardSchemaBean standardSchemaBean = new StandardSchemaBean();

                int dpId = functionSchemaListBean.getRelationDpIdMaps().get(functionSchemaListBean.getStandardCode());
                Object value = mDevBean.getDps().get("" + dpId);

                standardSchemaBean.setDpCode(functionSchemaListBean.getStandardCode());
                standardSchemaBean.setStandardType(functionSchemaListBean.getStandardType());
                standardSchemaBean.setValue(value);
                schemaBeanArrayList.add(standardSchemaBean);
            }
        } else {

//            standardSchemaBean.setDpCode(functionSchemaListBean.getStandardCode());
//            standardSchemaBean.setStandardType(functionSchemaListBean.getStandardType());

            mDevBean.getProductBean().getSchemaInfo();

            if (mDevBean == null) return new ArrayList<>();
            Map<String, SchemaBean> schemaMap = mDevBean.getSchemaMap();
            for (Map.Entry<String, SchemaBean> entry : schemaMap.entrySet()) {
                StandardSchemaBean standardSchemaBean = new StandardSchemaBean();
                standardSchemaBean.setDpCode(entry.getValue().getCode());

                String dpId = entry.getKey();
                Object value = mDevBean.getDps().get( dpId);
                standardSchemaBean.setDpCode(entry.getValue().getCode());
                standardSchemaBean.setValue(value);
                standardSchemaBean.setStandardType(entry.getValue().getSchemaType());
                schemaBeanArrayList.add(standardSchemaBean);
            }
        }


//
//        Map<String, SchemaBean> schemaMap = mDevBean.getSchemaMap();
//        List<SchemaBean> schemaBeanArrayList = new ArrayList<>();
//        for (Map.Entry<String, SchemaBean> entry : schemaMap.entrySet()) {
//            schemaBeanArrayList.add(entry.getValue());
//        }
//
//        Collections.sort(schemaBeanArrayList, new Comparator<SchemaBean>() {
//            @Override
//            public int compare(SchemaBean lhs, SchemaBean rhs) {
//                return Integer.valueOf(lhs.getId()) < Integer.valueOf(rhs.getId()) ? -1 : 1;
//            }
//        });
        return schemaBeanArrayList;
    }

    public void onClick(View view) {
        String dpID = (String) view.getTag(R.id.schemaId);
        switch (view.getId()) {
            case R.id.iv_sub:
                TextView subTV = (TextView) view.getTag(R.id.schemaView);
                String subV = subTV.getText().toString();
                break;
            case R.id.iv_add:
                TextView addTV = (TextView) view.getTag(R.id.schemaView);
                String addV = addTV.getText().toString();
                sendCommand(dpID, Integer.valueOf(addV) + 1);
                break;
            case R.id.tv_input_send:
                EditText inputSend = (EditText) view.getTag(R.id.schemaView);
                String inputV = inputSend.getText().toString();
                sendCommand(dpID, inputV);
                break;
            case R.id.tv_enum:
                String schemaId = (String) view.getTag(R.id.schemaId);
                StandSchema standSchema = mDevBean.getProductBean().getsSchema();


                List<StandSchema.FunctionSchemaListBean> functionSchemaListBeans = mDevBean.getProductBean().getsSchema().getFunctionSchemaList();

                EnumSchemaBean enumSchemaBean = new EnumSchemaBean();
                Set<String> convertResultSet = new HashSet<>();

                if (standSchema.getFunctionSchemaList().size() > 0) {
                    List<StandSchema.FunctionSchemaListBean> functionSchemaList = standSchema.getFunctionSchemaList();
                    for (StandSchema.FunctionSchemaListBean functionSchemaListBean : functionSchemaList) {
                        if (schemaId.equals(functionSchemaListBean.getStandardCode())) {
                            String strategyValue = functionSchemaListBean.getStrategyValue();
                            Map<String, Object> convertResult = new HashMap<>();
                            JSONObject jsonObject = JSON.parseObject(strategyValue);
                            if (jsonObject != null) {
                                Set<String> keySet = jsonObject.keySet();
                                for (String key : keySet) {
                                    JSONObject enumMapping = JSON.parseObject((String) jsonObject.get(key));
                                    if (enumMapping != null) {
                                        Set<String> enumMappingKeySet = enumMapping.keySet();
                                        convertResultSet.addAll(enumMappingKeySet);
                                    } else {
                                        L.e("EnumStrategy", "enumMapping value is empty");
                                    }
                                }
                            } else {
                                L.e("EnumStrategy", "strategyValue is empty");
                            }
                        }
                    }
                }

                enumSchemaBean.setRange(convertResultSet);

                if (enumSchemaBean.getRange().size() > 0) {
                    showEnumDialog(schemaId, enumSchemaBean.getRange());
                } else {
                    Map<String, SchemaBean> schemaMappass = mDevBean.getSchemaMap();
                    for (Map.Entry<String, SchemaBean> entry : schemaMappass.entrySet()) {
                        if (entry.getValue().getCode().equals(schemaId)) {
                            EnumSchemaBean enumSchemaBeand = SchemaMapper.toEnumSchema(entry.getValue().getProperty());
                            showEnumDialog(schemaId, enumSchemaBeand.getRange());
                        }
                    }

                }
                break;

            default:
                break;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position) {
        String dpID = (String) adapterView.getTag(R.id.schemaId);
        SchemaBean schemaBean = mDevBean.getSchemaMap().get(dpID);
        EnumSchemaBean enumSchemaBean = SchemaMapper.toEnumSchema(schemaBean.getProperty());
        Set<String> range = enumSchemaBean.getRange();
        String[] result = new String[range.size()];
        result = range.toArray(result);
        sendCommand(dpID, result[position]);
    }


    public void onCheckedChanged(CompoundButton compoundButton, boolean value) {
        sendCommand((String) compoundButton.getTag(R.id.schemaId), value);
    }

    private void sendCommand(String dpId, Object value) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put(dpId, value);
//        if (!DevUtil.checkSendCommond(mDevId, stringObjectHashMap)) {
//            ToastUtil.showToast(mContext, "数据格式非法");
//            return;
//        }
        if (mDownLatch != null) return;
        mDownLatch = new LogCountDownLatch(1);

        String commandStr = JSON.toJSONString(stringObjectHashMap);
        mDownLatch.setDpSend(commandStr);
        mDownLatch.setTimeStart(System.currentTimeMillis());
        mDownLatch.setDpId(dpId);
        mTuyaDevice.publishCommands(stringObjectHashMap, new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                mDownLatch.setStatus(LogCountDownLatch.STATUS_FAILURE);
                mDownLatch.setErrorMsg(mContext.getString(R.string.send_failure) + " " + error);
                mDownLatch.countDown();
            }

            @Override
            public void onSuccess() {
                Log.i("CommonDeviceDebugPresenter", "onSuccess");
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mDownLatch.await(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mDownLatch == null) return;
                mDownLatch.setTimeEnd(System.currentTimeMillis());
                switch (mDownLatch.getStatus()) {
                    case LogCountDownLatch.STATUS_SUCCESS:
                        mView.logSuccess(mDownLatch.getLogBean());
                        break;
                    case LogCountDownLatch.STATUS_FAILURE:
                        mView.logError(mDownLatch.getLogBean());
                        break;
                    case LogCountDownLatch.STATUS_TIME_OUT:
                        mDownLatch.setErrorMsg(mContext.getString(R.string.send_time_out));
                        mView.logError(mDownLatch.getLogBean());
                        break;
                }
                mDownLatch = null;
            }
        }).start();
    }

    private void showEnumDialog(final String schemaId, Set<String> range) {
        ArrayList<String> rangesKey = new ArrayList<>();
        ArrayList<String> rangesValue = new ArrayList<>();
        for (String str : range) {
            rangesValue.add(str);
            rangesKey.add(str);
        }
        final AlertPickBean alertPickBean = new AlertPickBean();
        alertPickBean.setLoop(true);
        alertPickBean.setCancelText(mContext.getString(R.string.cancel));
        alertPickBean.setConfirmText(mContext.getString(R.string.confirm));
        alertPickBean.setRangeKeys(rangesKey);
        alertPickBean.setRangeValues(rangesValue);
        alertPickBean.setTitle(String.format(mContext.getString(R.string.choose_dp_value), schemaId));
        AlertPickDialog.showAlertPickDialog((Activity) mContext, alertPickBean, new AlertPickDialog.AlertPickCallBack() {
            @Override
            public void confirm(String value) {
                String dpId = schemaId;
                sendCommand(dpId, value);
            }

            @Override
            public void cancel() {

            }
        });
    }

    @Override
    public void onDpUpdate(String devId, Map<String, Object> dpStr) {
        String dpStdddr = JSONObject.toJSONString(dpStr);
        mView.updateView(dpStdddr);

        boolean isFromCloud = true;
        DeviceBean deviceBean = TuyaHomeSdk.getDataInstance().getDeviceBean(devId);
        if (deviceBean != null && deviceBean.getIsLocalOnline()) {
            isFromCloud = false;
        }
        mView.logDpReport((isFromCloud ? "Cloud" : "local area network") + " " + dpStr);
        JSONObject jsonObject = getDpValueWithOutROMode(devId, dpStdddr);
        if (mDownLatch != null && mDownLatch.getCount() > 0 && !jsonObject.isEmpty()) {
            Object o = jsonObject.get(mDownLatch.getDpId());
            if (o != null) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put(mDownLatch.getDpId(), o);
                if (TextUtils.equals(JSONObject.toJSONString(hashMap), mDownLatch.getDpSend())) {
                    mDownLatch.setStatus(LogCountDownLatch.STATUS_SUCCESS);
                } else {
                    mDownLatch.setStatus(LogCountDownLatch.STATUS_FAILURE);
                }
                mDownLatch.setDpReturn(dpStdddr);
                mDownLatch.countDown();
            }
        }
    }

    public JSONObject getDpValueWithOutROMode(String devId, String value) {
        JSONObject jsonObject = JSONObject.parseObject(value);
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String dpId = entry.getKey();
            Map<String, SchemaBean> schema = TuyaHomeSdk.getDataInstance().getSchema(devId);
            if (schema != null) {
                SchemaBean schemaBean = schema.get(dpId);
                if (schemaBean != null && TextUtils.equals(schemaBean.getMode(), ModeEnum.RO.getType())) {
                    list.add(dpId);
                }
            }
        }
        for (String dpId : list) {
            jsonObject.remove(dpId);
        }
        return jsonObject;
    }


    public static class LogCountDownLatch extends CountDownLatch {
        private int status = STATUS_TIME_OUT;
        public static final int STATUS_SUCCESS = 1;
        public static final int STATUS_FAILURE = 2;
        public static final int STATUS_TIME_OUT = 3;
        private long timeStart;
        private long timeEnd;
        private String dpSend;
        private String dpReturn;
        private String mDpId;
        private String mErrorMsg;

        public LogCountDownLatch(int time) {
            super(time);
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getTimeStart() {
            return timeStart;
        }

        public void setTimeStart(long timeStart) {
            this.timeStart = timeStart;
        }

        public long getTimeEnd() {
            return timeEnd;
        }

        public void setTimeEnd(long timeEnd) {
            this.timeEnd = timeEnd;
        }

        public String getDpSend() {
            return dpSend;
        }

        public void setDpSend(String dpSend) {
            this.dpSend = dpSend;
        }

        public String getDpReturn() {
            return dpReturn;
        }

        public void setDpReturn(String dpReturn) {
            this.dpReturn = dpReturn;
        }

        public void setDpId(String dpId) {
            mDpId = dpId;
        }

        public String getDpId() {
            return mDpId;
        }

        public DpLogBean getLogBean() {
            return new DpLogBean(timeStart, timeEnd, dpSend, dpReturn, mErrorMsg);
        }

        public void setErrorMsg(String errorMsg) {
            mErrorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return mErrorMsg;
        }
    }


    @Override
    public void onRemoved(String devId) {
        mView.deviceRemoved();
    }

    @Override
    public void onStatusChanged(String devId, boolean online) {
        mView.deviceOnlineStatusChanged(online);
    }

    @Override
    public void onNetworkStatusChanged(String devId, boolean status) {
        mView.onNetworkStatusChanged(status);
    }

    @Override
    public void onDevInfoUpdate(String devId) {
        mView.devInfoUpdate();
    }


    public void renameDevice() {
        DialogUtil.simpleInputDialog(mContext, mContext.getString(R.string.rename), getTitle(), false, new DialogUtil.SimpleInputDialogInterface() {
            @Override
            public void onPositive(DialogInterface dialog, String inputText) {
                int limit = mContext.getResources().getInteger(R.integer.change_device_name_limit);
                if (inputText.length() > limit) {
                    ToastUtil.showToast(mContext, R.string.ty_modify_device_name_length_limit);
                } else {
                    renameTitleToServer(inputText);
                }
            }

            @Override
            public void onNegative(DialogInterface dialog) {

            }
        });
    }

    private void renameTitleToServer(final String titleName) {
        ProgressUtil.showLoading(mContext, R.string.loading);
        mTuyaDevice.renameDevice(titleName, new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                ProgressUtil.hideLoading();
                ToastUtil.showToast(mContext, error);
            }

            @Override
            public void onSuccess() {
                ProgressUtil.hideLoading();
                mView.updateTitle(titleName);
            }
        });

    }


    public void resetFactory() {
        DialogUtil.simpleConfirmDialog(mContext, mContext.getString(R.string.ty_control_panel_factory_reset_info),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            ProgressUtil.showLoading(mContext, R.string.ty_control_panel_factory_reseting);
                            mTuyaDevice.resetFactory(new IResultCallback() {
                                @Override
                                public void onError(String code, String error) {
                                    ProgressUtil.hideLoading();
                                    ToastUtil.shortToast(mContext, R.string.ty_control_panel_factory_reset_fail);
                                }

                                @Override
                                public void onSuccess() {
                                    ProgressUtil.hideLoading();
                                    ToastUtil.shortToast(mContext, R.string.ty_control_panel_factory_reset_succ);
                                    ((Activity) mContext).finish();
                                }
                            });
                        }
                    }
                });
    }


    public void removeDevice() {
        ProgressUtil.showLoading(mContext, R.string.loading);
        mTuyaDevice.removeDevice(new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                ProgressUtil.hideLoading();
                ToastUtil.showToast(mContext, error);
            }

            @Override
            public void onSuccess() {
                ProgressUtil.hideLoading();
                ((Activity) mContext).finish();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDownLatch != null) {
            mDownLatch.countDown();
        }
//        mDownLatch = null;
        if (mTuyaDevice != null) mTuyaDevice.onDestroy();

    }
}
