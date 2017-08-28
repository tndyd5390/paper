package com.paper.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paper.persistance.mapper.PaperMapper;
import com.paper.service.IPaperService;

@Service("PaperService")
public class PaperService implements IPaperService{
	@Resource(name="PaperMapper")
	private PaperMapper paperMapper;
}
