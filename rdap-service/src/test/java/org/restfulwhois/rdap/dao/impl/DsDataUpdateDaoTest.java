/*
 * Copyright (c) 2012 - 2015, Internet Corporation for Assigned Names and
 * Numbers (ICANN) and China Internet Network Information Center (CNNIC)
 * 
 * All rights reserved.
 *  
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  
 * * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * * Neither the name of the ICANN, CNNIC nor the names of its contributors may
 *  be used to endorse or promote products derived from this software without
 *  specific prior written permission.
 *  
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL ICANN OR CNNIC BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */
package org.restfulwhois.rdap.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.restfulwhois.rdap.BaseTest;
import org.restfulwhois.rdap.common.dao.UpdateDao;
import org.restfulwhois.rdap.common.model.Domain;
import org.restfulwhois.rdap.common.model.DsData;
import org.restfulwhois.rdap.common.model.Link;
import org.restfulwhois.rdap.common.model.base.BaseModel;
import org.restfulwhois.rdap.common.model.base.ModelType;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

/**
 * @author zhanyq
 * 
 */
public class DsDataUpdateDaoTest extends BaseTest {

	 private static final String TABLE_RDAP_DSDATA= "RDAP_DSDATA";
	 private static final String TABLE_REL_SECUREDNS_DSKEY = "REL_SECUREDNS_DSKEY";	 
	 private static final String TABLE_RDAP_LINK = "RDAP_LINK";
	 private static final String TABLE_REL_LINK_OBJECT = "REL_LINK_OBJECT";
	 private static final String TABLE_RDAP_LINK_HREFLANG = "RDAP_LINK_HREFLANG";
	// private static final String TABLE_RDAP_EVENT= "RDAP_EVENT";
	// private static final String TABLE_REL_EVENT_REGISTRATION = "REL_EVENT_REGISTRATION";

	    @Autowired
	    private UpdateDao<DsData,BaseModel> updateDao;

	    @Test
	    @DatabaseSetup("teardown.xml")
	    @DatabaseTearDown("teardown.xml")   
	    public void testcreateDsData() throws Exception {
	    	Domain domain = new Domain();
	    	domain.setId(1L);
	    	List<DsData> dsDataList = new ArrayList<DsData>();	    	
	    	DsData dsData = new DsData();
	    	dsData.setAlgorithm(1);
	    	dsData.setDigest("1-ds-dt-200-50");
	    	dsData.setDigestType(1);
	    	dsData.setKeyTag(1);
	    	
	    	
	    	//link
	    	List<Link> linkList = new ArrayList<Link>();
	    	List<String> hreflang = new ArrayList<String>();
	    	hreflang.add("en");
	    	hreflang.add("zh");
	    	Link link = new Link();
	    	link.setHref("http://sina.com.cn");
	    	link.setMedia("screen");
	    	link.setRel("up");
	    	link.setTitle("little title");
	    	link.setType("application/rdap+json");
	    	link.setValue("http://sina.com.cn");
	    	link.setHreflang(hreflang);
	    	linkList.add(link);
	    	dsData.setLinks(linkList);
	    
	    	dsDataList.add(dsData);
	        updateDao.batchCreateAsInnerObjects(domain, dsDataList);
	        super.assertTablesForUpdate("dsData-update.xml", TABLE_RDAP_DSDATA,TABLE_REL_SECUREDNS_DSKEY,
	        		TABLE_RDAP_LINK,TABLE_REL_LINK_OBJECT,TABLE_RDAP_LINK_HREFLANG);
	    }
}
