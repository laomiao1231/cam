package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.model.LostItems;
import com.m.service.LostItemsService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lostItems")
public class LostItemsController {
    @Autowired
    private LostItemsService lostItemsService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String saveLostItems(LostItems lostItems) {
        lostItems.setLostItemsDate(new Date());
        lostItems.setLostItemsDescribe("Ò»´®Ô¿³×");
        lostItems.setLostItemsStatus(0);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.lostItemsService.save(lostItems);
            map.put("status", 200);
        }catch (Exception e){
            map.put("status", 400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/remove/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String removeLostItemsById(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.lostItemsService.removeById(Id);
            map.put("status", 200);
        }catch (Exception e){
            map.put("status", 400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String updateLostItems(@PathVariable("Id") Integer Id, LostItems lostItems) {
        lostItems.setLostItemsId(Id);
        lostItems.setLostItemsDate(new Date());
        lostItems.setLostItemsDescribe("Ô¿³×");
        lostItems.setLostItemsStatus(1);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.lostItemsService.update(lostItems);
            map.put("status", 200);
        }catch (Exception e){
            map.put("status", 400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/get/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public LostItems getLostItemsById(@PathVariable("Id") Integer Id) {
        LostItems lostItems = this.lostItemsService.getById(Id);
        return lostItems;
    }

    @RequestMapping(value = "/loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public List<LostItems> loadAllLostItems(@RequestParam("pageNumber") Integer pageNumber,
                                            @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);
        List<LostItems> lostItemsList = this.lostItemsService.loadAll(map);
        return lostItemsList;
    }

    @RequestMapping(value = "/upload", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public void upload(HttpServletRequest request,
                       @RequestParam("image") MultipartFile file) throws Exception{
        String path = request.getServletContext().getRealPath("/image/"+file.getOriginalFilename());
        System.out.println(request.getServletContext().getRealPath("/image/"+file.getOriginalFilename()));
        File tempFile = new File(path);
        file.transferTo(tempFile);
    }

    @RequestMapping(value = "/download/{fileName}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @PathVariable("fileName") String fileName) throws Exception {
        String ext = ".jpg";
        fileName = fileName + ext;
        String path = request.getServletContext().getRealPath("/image/");
        File file = new File(path + fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        //String downloadFileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        //httpHeaders.setContentDispositionFormData("attachment", downloadFileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByKey", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public List<LostItems> getLostItemsByKey(@RequestParam("keyWord") String keyWord) {
        List<LostItems> lostItemsList = this.lostItemsService.getByKey(keyWord);
        return lostItemsList;
    }
}
