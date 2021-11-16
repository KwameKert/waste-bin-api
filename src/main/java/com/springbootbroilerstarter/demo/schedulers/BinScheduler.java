package com.springbootbroilerstarter.demo.schedulers;

import com.springbootbroilerstarter.demo.dtos.ChannelFeed;
import com.springbootbroilerstarter.demo.dtos.Feed;
import com.springbootbroilerstarter.demo.services.interfaces.BinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BinScheduler {

    @Autowired
    private BinService binService;

    private static final Logger LOG = LoggerFactory.getLogger(BinScheduler.class);

    @Scheduled(fixedDelay = 5000)
    public void checkBinFull(){
        RestTemplate restTemplate = new RestTemplate();
        String thinkSpeakUrl
                = "https://api.thingspeak.com/channels/1545848/feeds.json?results=1";
        ResponseEntity<ChannelFeed> response
                = restTemplate.getForEntity(thinkSpeakUrl , ChannelFeed.class);
       // System.out.println(response);
        Feed feed = response.getBody().feeds.get(0);
        if(feed.field1 != null){
            System.out.println(feed.field1);
            int binOneStatus = Integer.parseInt(response.getBody().feeds.get(0).field1) <= 33 ? 1: 0;
            this.binService.updateBinSensor(binOneStatus, 1L);
        }else if(feed.field2 != null){
            System.out.println(feed.field2);
            int binOneStatus = Integer.parseInt(response.getBody().feeds.get(0).field2) <= 33 ? 1: 0;
            this.binService.updateBinSensor(binOneStatus, 2L);
        }

        LOG.info("Fetching data from thinkspeak ---> "+response.getBody().channel.name);
    }

}
