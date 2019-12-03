package com.example.demo.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.PhotoDao;
import com.example.demo.dao.SiteDao;
import com.example.demo.module.Photo;
import com.example.demo.module.Site;
@RequestMapping("/sites")
@Controller
public class SiteController {
	public String fileLocation = System.getProperty("user.dir")+"/uploadingDir/";

	@Autowired
	public SiteDao siteDao;
	@Autowired
	public PhotoDao photoDao;
	
	




	@RequestMapping(value = "/addsite" ,method = RequestMethod.GET)
	public String addSite(Model model) {
		Site site=new Site();
		File file = new File(fileLocation);
        model.addAttribute("files", file.listFiles());
		model.addAttribute("site",site);
		return "NewFile";
	}
	
	@GetMapping("/")
	public String index(Model model)
	{
		List<Site> sites = siteDao.findAll();
		model.addAttribute("sites",sites);
		return "index";
	}
	
	
	@RequestMapping(value = "/addsite" ,method = RequestMethod.POST)
	public String addSiteform(Model model,@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,Site site) {
			
		siteDao.save(site);
	
		for(MultipartFile uploadedFile : uploadingFiles) {
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//			   LocalDateTime now = LocalDateTime.now();  
			String filename=uploadedFile.getOriginalFilename();
			System.out.println(filename);
//            File file = new File(fileLocation+ dtf.format(now)+ filename);
            File file = new File(fileLocation+filename);

            try {
				uploadedFile.transferTo(file);
				Photo picture=new Photo(filename,site);
				photoDao.save(picture);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return "index";
	}
	
	
}
