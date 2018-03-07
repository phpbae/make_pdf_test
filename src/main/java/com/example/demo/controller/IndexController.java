package com.example.demo.controller;


import com.example.demo.component.PdfFileMakerComponent;
import com.example.demo.component.ZipFileMakerComponent;
import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PdfFileMakerComponent pdfFileMakerComponent;

    @Autowired
    private ZipFileMakerComponent zipFileMakerComponent;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {

        return new ModelAndView("index");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> test() {

        List<Member> memberList = memberService.getMembers();
        File resultFile = pdfFileMakerComponent.testCreatePDF(memberList.get(0));

        InputStreamResource resource = null;
        HttpHeaders headers = null;
        try {
            resource = new InputStreamResource(new FileInputStream(resultFile));
            headers = new HttpHeaders();
            headers.add("Content-Transfer-Encoding", "binary");
            try {
                headers.add("Content-Disposition", "inline; filename=" + URLEncoder.encode(resultFile.getName(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resultFile.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @RequestMapping(value = "test2", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> test2() {
        List<Member> memberList = memberService.getMembers();
        List<List<Member>> lists = Lists.partition(memberList, 100);

        List<File> files = lists.stream()
                .map(membersList -> pdfFileMakerComponent.executeCreatePDF(membersList))
                .collect(Collectors.toList());

        File zipFile = zipFileMakerComponent.createZipFile(files);

        InputStreamResource resource = null;
        HttpHeaders headers = null;
        try {
            resource = new InputStreamResource(new FileInputStream(zipFile));
            headers = new HttpHeaders();
            headers.add("Content-Transfer-Encoding", "binary");
            try {
                headers.add("Content-Disposition", "inline; filename=" + URLEncoder.encode(zipFile.getName(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(zipFile.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }

}
