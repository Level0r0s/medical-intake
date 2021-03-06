package com.neil.medical.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neil.medical.service.PrisonIntakeRecord;
import com.neil.medical.service.RefuseIntake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nhu on 4/23/2017.
 */
@Controller
public class PrisonIntakeController {


    @Autowired
    private RefuseIntake refuseIntake;

    @Autowired
    private PrisonIntakeRecord intakeRecord;

    @RequestMapping(value = "intake/miss", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateCheckedRecords(@RequestBody JSONArray records) {
        refuseIntake.updateCheckedRecords(records);
        return new JSONObject();
    }


    @RequestMapping(value = "intake/miss")
    @ResponseBody
    public List<JSONObject> findMissIntakeRecording() {
        return refuseIntake.getAllRefusingRecord();
    }

    @RequestMapping(value = "intake/miss/{code}/{date}")
    @ResponseBody
    public List<JSONObject> findPrisonMissIntakeRecords(@PathVariable String code, @PathVariable String date){
        return refuseIntake.getPrisonMissIntakeRecords(code, date);
    }

    @RequestMapping(value = "intake/{code}/{timespan}/{time}")
    @ResponseBody
    public List<JSONObject> findIntakeRecording(@PathVariable String code, @PathVariable String timespan, @PathVariable String time) {
        return intakeRecord.getPrisonIntakeRecord(code, timespan, time);
    }


    @RequestMapping(value = "inmate/intake/{code}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject inmateMedicalIntake(@PathVariable String code, @RequestBody List<JSONObject> medicals) {
        intakeRecord.inmateConfirmMedicalIntake(code, medicals);
        return new JSONObject();
    }

    @RequestMapping(value = "video-record/{time}")
    @ResponseBody
    public JSONObject findVideo(@PathVariable String time){
        return intakeRecord.findVideo(time);
    }
}
