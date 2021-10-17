package com.springbootbroilerstarter.demo.schedulers;

import com.springbootbroilerstarter.demo.dtos.ChannelFeed;
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

    @Scheduled(fixedRate = 30000000)
    public void checkBinFull(){
        RestTemplate restTemplate = new RestTemplate();
        String thinkSpeakUrl
                = "https://api.thingspeak.com/channels/1476474/feeds.json?results=1";
        ResponseEntity<ChannelFeed> response
                = restTemplate.getForEntity(thinkSpeakUrl , ChannelFeed.class);
        int status = Integer.parseInt(response.getBody().feeds.get(0).field1) <= 33 ? 1: 0;
        this.binService.updateBinSensor(status);
        LOG.info("Fetching data from thinkspeak ---> "+response.getBody().channel.name);
    }

}
