package me.itchalleges.notwitter.rest;

import me.itchalleges.notwitter.model.rest.ChirikDto;
import me.itchalleges.notwitter.model.rest.ChirikRequest;
import me.itchalleges.notwitter.service.IChirikService;
import me.itchalleges.notwitter.service.IMessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Date: 11/05/2016 2:47 PM
 */
@RestController
@RequestMapping("/chirik")
public class ChirikController {
    private static final Logger LOG = Logger.getLogger(ChirikController.class);

    @Autowired
    private IMessageService messages;
    @Autowired
    private IChirikService chirikService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> createChirik(@RequestBody ChirikRequest chirikRequest) {
        try {
            String id = chirikService.createChirik(chirikRequest);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            e.printStackTrace();
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ChirikDto>> getOwn(@RequestParam("tokenId") String tokenId) {
        try {
            List<ChirikDto> ownList = chirikService.getOwn(tokenId);
            return ResponseEntity.ok(ownList);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/following", method = RequestMethod.GET)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ChirikDto>> getFollowing(@RequestParam("tokenId") String tokenId) {
        try {
            List<ChirikDto> followList = chirikService.getFollowing(tokenId);
            return ResponseEntity.ok(followList);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/mention", method = RequestMethod.GET)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ChirikDto>> getMention(@RequestParam("tokenId") String tokenId) {
        try {
            List<ChirikDto> mentionList = chirikService.getMention(tokenId);
            return ResponseEntity.ok(mentionList);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/wall", method = RequestMethod.GET)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ChirikDto>> getWall(@RequestParam("tokenId") String tokenId) {
        try {
            List<ChirikDto> followList = chirikService.getWall(tokenId);
            return ResponseEntity.ok(followList);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/mentionByUser", method = RequestMethod.GET)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ChirikDto>> getMentionByUser(@RequestParam("userName") String userName) {
        try {
            List<ChirikDto> mentionList = chirikService.getMentionByUser(userName);
            return ResponseEntity.ok(mentionList);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
