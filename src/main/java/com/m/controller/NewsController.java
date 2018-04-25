package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m.model.News;
import com.m.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String saveNews(News news) {
        news.setNewsTitle("test");
        news.setNewsContent("this is test");
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

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String updateNews(@PathVariable("Id") Integer Id, News news) {
        news.setNewsId(Id);
        news.setNewsTitle("update");
        news.setNewsContent("this is a update");
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
        Map<String, Integer> map = new HashMap<>();
        /*Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);*/
        PageHelper.startPage(pageNumber, pageSize);
        List<News> newsList = this.newsService.loadAll(map);
        PageInfo<News> pageInfo = new PageInfo<>(newsList, pageSize);
        System.out.println(pageInfo.getList().size());
        return pageInfo;
    }

    @RequestMapping(value = "/getByKey", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    @ResponseBody
    public List<News> getNewsByKey(@RequestParam("keyWord") String keyWord) {
        List<News> newsList = this.newsService.getByKey(keyWord);
        return newsList;
    }
}
