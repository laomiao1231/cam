package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m.model.News;
import com.m.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/news")
public class NewsController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveNews(News news) throws Exception {
        news.setNewsTime(new Date());
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.newsService.save(news);
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
    public String removeNewsById(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.newsService.removeById(Id);
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

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateNews(@PathVariable("Id") Integer Id, News news) {
        news.setNewsId(Id);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.newsService.update(news);
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
    public News getNewsById(@PathVariable("Id") Integer Id) {
        News news = this.newsService.getById(Id);
        return news;
    }

    @RequestMapping(value = "/loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<News> loadAllNews(@RequestParam(defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                  @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        System.out.println(".......PageHelper"+PageHelper.startPage(pageNumber, pageSize));
        Map<String, Integer> map = new HashMap<>();
        /*Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);*/
        List<News> newsList = this.newsService.loadAll(map);
        PageInfo<News> pageInfo = new PageInfo<>(newsList, pageSize);
        return pageInfo;
    }

    @RequestMapping(value = "/getByKey", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<News> getNewsByKey(@RequestParam(defaultValue = "1", value = "pageNumber") Integer pageNumber,
                                   @RequestParam("pageSize") Integer pageSize,@RequestParam("keyWord") String keyWord) {
        PageHelper.startPage(pageNumber,pageSize);
        List<News> newsList = this.newsService.getByKey(keyWord);
        PageInfo<News> pageInfo = new PageInfo<>(newsList, 5);
        return pageInfo;
    }

    @RequestMapping(value = "/getDetail/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public String getNewsDetailById(@PathVariable("Id") Integer Id,Map<String,Object> map) {
        News news = this.newsService.getById(Id);
        map.put("news", news);
        return "news/news_detail";
    }
}
