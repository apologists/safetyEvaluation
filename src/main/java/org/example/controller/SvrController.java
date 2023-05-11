package org.example.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


//返回json字符串的数据，直接可以编写RESTFul的接口
@RestController
@RequestMapping("/api")
public class SvrController {

    /*********************************************************************/
    //仅针对旧板协议才有IsConnect接口(非6.0开头的)
    @RequestMapping(value = "/IsConnect", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")//访问地址与请求方法，
    public @ResponseBody
    String IsConnect(@RequestBody String params) { //获取body里的json数据并返回在body里·
        StringBuilder sb = new StringBuilder();
        try {
            //接收到的数据格式  {"ViewId":"1","UID":"123","UKey":"1234","SN":"170000001","TamperAlarm":"0","DoorMagnetic":"0","Timestamp":"1594198847962","Sign":"1fcc27459b99d16ee5ba2dc6e657d5c8"}
            sb.append(URLDecoder.decode(params, "UTF-8"));
            if (sb.length() > 11 && sb.substring(0, 11).equals("paramaters=")) //旧接口协议有前缀，判断一下，去掉
                sb.delete(0, 11); //去掉paramaters=
            System.out.println("IsConnect:" + sb);
            //解析json
            JSONObject json = JSON.parseObject(sb.toString());
            String SN = json.getString("SN");//卡号
            //......以下写业务逻辑
            //................

            //返回数据
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("DateTime", formatter.format(new Date()));//返回服务器时间给设备

            String ret = jsonObj.toJSONString(); //返回格式：{"DateTime":"2020-09-27 15:57:41"}
            System.out.println("Return IsConnect:" + ret);
            return ret;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("Ex:" + e.getMessage());
        }
        return "";
    }

    /*********************************************************************/
    //新版协议(6.0开头的版本)，将心跳与远程接口合并为一个，即为QueryCmd
    @RequestMapping(value = "/QueryCmd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")//访问地址与请求方法，
    public @ResponseBody
    String QueryCmd(@RequestBody String params) { //获取body里的json数据并返回在body里·
        StringBuilder sb = new StringBuilder();
        try {
            //接收到的数据格式  {"ViewId":"1","UID":"123","UKey":"1234","SN":"170000001","TamperAlarm":"0","DoorMagnetic":"0","Timestamp":"1594198847962","Sign":"1fcc27459b99d16ee5ba2dc6e657d5c8"}
            sb.append(URLDecoder.decode(params, "UTF-8"));
            if (sb.length() > 11 && sb.substring(0, 11).equals("paramaters=")) //旧接口协议有前缀，判断一下，去掉
                sb.delete(0, 11); //去掉paramaters=
            System.out.println("QueryCmd:" + sb);
            //解析json
            JSONObject json = JSON.parseObject(sb.toString());
            String SN = json.getString("SN");//卡号
            //......以下写业务逻辑
            //................

            //返回数据
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int CmdCode = 0;//0为无命令，即心跳返回
            String CmdID = String.valueOf(System.currentTimeMillis());//CmdID可定义

            JSONObject jsonObj = new JSONObject();
            JSONObject jsonObjCmd0 = new JSONObject();
            jsonObjCmd0.put("DateTime", formatter.format(new Date()));//返回服务器时间给设备
            jsonObj.put("CmdID", CmdID);
            jsonObj.put("CmdCode", CmdCode);
            jsonObj.put("CmdParams", jsonObjCmd0);

            String ret = jsonObj.toJSONString(); //无命令返回格式：{"CmdCode":0,"CmdID":"1601193461935","CmdParams":{"DateTime":"2020-09-27 15:57:41"}}
            System.out.println("Return QueryCmd:" + ret);
            return ret;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("Ex:" + e.getMessage());
        }
        return "";
    }

    /*********************************************************************/
    //定义一个验证接口
    @RequestMapping(value = "/CheckCode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")//访问地址与请求方法
    public @ResponseBody
    String CheckCode(@RequestBody String params) { //获取body里的json数据并返回在body里·
        StringBuilder sb = new StringBuilder();
        try {
            //接收到的数据格式  {"CodeVal":"985","CodeType":"Q","BrushTime":"2020-09-24 09:17:21","ViewId":"1","UID":"123","UKey":"1234","SN":"170000001","IsOnline":"1","Property":"1","Timestamp":"1600910241600","Sign":"ed097d71179890af524759daacaea06f"}
            sb.append(URLDecoder.decode(params, "UTF-8"));
            if (sb.length() > 11 && sb.substring(0, 11).equals("paramaters=")) //旧接口协议有前缀，判断一下，去掉
                sb.delete(0, 11); //去掉paramaters=
            System.out.println("CheckCode:" + sb);
            //解析json
            JSONObject json = JSON.parseObject(sb.toString());
            String SN = json.getString("SN");//卡号
            //......以下写业务逻辑
            //................

            //返回数据
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("Status", 1);
            jsonObj.put("StatusDesc", "验票成功\r\n请进");
            jsonObj.put("Relay1Time", 1000);
            jsonObj.put("TurnGateTimes", 1);
            //jsonObj.put("TTS","验票成功");

            String ret = jsonObj.toJSONString(); //返回格式：{"Status":1,"StatusDesc":"验票成功\r\n请进","TurnGateTimes":1,"Relay1Time":1000}
            System.out.println("Return CheckCode:" + ret);
            return ret;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("Ex:" + e.getMessage());
        }
        return "";
    }

    /*********************************************************************/
    @RequestMapping(value = "/CheckCodeWalkPast", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//访问地址与请求方法
    public @ResponseBody
    String CheckCodeWalkPast(@RequestBody String params) { //获取body里的json数据并返回在body里·
        StringBuilder sb = new StringBuilder();
        try {
            //接收到的数据格式  {"CodeVal":"985","CodeType":"Q","BrushTime":"2020-09-24 09:17:21","ViewId":"1","UID":"123","UKey":"1234","SN":"170000001","IsOnline":"1","Property":"1","Timestamp":"1600910241600","Sign":"ed097d71179890af524759daacaea06f"}
            sb.append(URLDecoder.decode(params, "UTF-8"));
            if (sb.length() > 11 && sb.substring(0, 11).equals("paramaters=")) //旧接口协议有前缀，判断一下，去掉
                sb.delete(0, 11); //去掉paramaters=
            System.out.println("CheckCodeWalkPast:" + sb);
            //解析json
            JSONObject json = JSON.parseObject(sb.toString());
            String SN = json.getString("SN");//卡号
            //......以下写业务逻辑
            //................

            //返回数据
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("Status", 0);

            String ret = jsonObj.toJSONString(); //返回格式：{"Status":0}
            System.out.println("Return CheckCodeWalkPast:" + ret);
            return ret;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("Ex:" + e.getMessage());
        }
        return "";
    }
}
