package com.sip.ams.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Candidat;

@Controller
public class HomeController {
	static ArrayList<Candidat> lc;
	static {
		lc = new ArrayList<>();
		Candidat c1 = new Candidat(0,"Naouel","naouel@gmail.com","11111111");
		Candidat c2 = new Candidat(1,"Sameh","samed@gmail.com","22222222");
		Candidat c3 = new Candidat(2,"Amine","amine@gmail.com","33333333");
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
	}
	
	@RequestMapping("/index")
	//@ResponseBody
	public String home()
	{
		//return "<h2>Bienvenue au BootCamp</h2>";
		return "home/index";
	}
	
	@RequestMapping("/candidats")
	public String listCandidats(Model m)
	{
		/*
		Candidat tab[]=new Candidat[3];
		tab[0]=c1;
		tab[1]=c2;
		tab[2]=c3;*/
		String libelleFormation ="Spring Boot & Angular";
		String formateur = "Mohamed Amine Mezghich";
		
		m.addAttribute("lf", libelleFormation);
		m.addAttribute("coach", formateur);
		m.addAttribute("tab",lc);
		return "home/candidats";
	}
	
	@GetMapping("/add")
	//@ResponseBody
	public String addCandidate()
	{
		return "home/add";
	}
	
	@PostMapping("/add")
	//@ResponseBody
	public String saveCandidate(@RequestParam("id") int id, 
			                    @RequestParam("nom") String nom,
			                    @RequestParam("email") String email,
			                    @RequestParam("tel") String tel)
	{
		Candidat temp = new Candidat(id,nom,email,tel);
		lc.add(temp);
		
		//System.out.println(id+" "+nom+" "+email+" "+tel);
		
		//return "infos : "+id+" "+nom+" "+email+" "+tel;
		return "redirect:candidats";
	}
	
	@GetMapping("/show/{idC}")
	@ResponseBody
	public String show(@PathVariable("idC")int id)
	{
		return "ID : "+id;
		//return"home/show";
	}
	
	@GetMapping("/delete/{idC}")
	public String delete(@PathVariable("idC")int id)
	{
		lc.remove(id);
		return "redirect:../candidats";
	}
	
	

}
