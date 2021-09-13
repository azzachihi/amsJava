package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Candidat;

@Controller
public class HomeController {
	@RequestMapping("/index")
	public String  home()
	{
		return "home/index";
	}
	@RequestMapping("/candidats")
	public String listeCandidat(Model m)
	{
		//bch nesna3 tableau dobjet candidat
		Candidat c1 = new Candidat(1,"nawel","nawel@gmail.com","21212121");
		Candidat c2 = new Candidat(2,"sameh","sameh@gmail.com","44444121");
		Candidat c3 = new Candidat(3,"mohamed","mohamed@gmail.com","8888888888");
		Candidat tab[]= new Candidat[3];
		tab[0]=c1;
		tab[1]=c2;
		tab[2]=c3;
		
		String libeleFormation = "springBoot angular";
		m.addAttribute("lf", libeleFormation);
		m.addAttribute("tab", tab);
		return "home/candidats";
		
		
	}
	@GetMapping("/add")
	public String add()
	{
		return "home/add";
	}

}
