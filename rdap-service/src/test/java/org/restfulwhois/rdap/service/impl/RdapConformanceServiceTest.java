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
package org.restfulwhois.rdap.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.restfulwhois.rdap.BaseTest;
import org.restfulwhois.rdap.common.model.Autnum;
import org.restfulwhois.rdap.common.model.Domain;
import org.restfulwhois.rdap.common.service.RdapConformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

/**
 * Test for RdapConformanceServiceImpl.
 * 
 * @author jiashuo
 * 
 */
@SuppressWarnings("rawtypes")
public class RdapConformanceServiceTest extends BaseTest {
	@Autowired
	private RdapConformanceService rdapConformanceService;

	/**
	 * test query exist autnum
	 */
	@Test
	@DatabaseTearDown("classpath:org/restfulwhois/rdap/dao/impl/teardown.xml")
	@DatabaseSetup("classpath:org/restfulwhois/rdap/dao/impl/rdapConformance.xml")
	public void testSetRdapConformanceToAutnum() {
		Autnum autnum = new Autnum();
		Assert.notNull(autnum);
		assertNull(autnum.getRdapConformance());
		rdapConformanceService.initRdapConformance();
		rdapConformanceService.setRdapConformance(autnum);
		assertNotNull(autnum.getRdapConformance());
		assertThat(autnum.getRdapConformance(),
				CoreMatchers.hasItem("rdap_level_0"));
	}

	/**
	 * test query exist domain
	 */
	@Test
	@DatabaseTearDown("classpath:org/restfulwhois/rdap/dao/impl/teardown.xml")
	@DatabaseSetup("classpath:org/restfulwhois/rdap/dao/impl/rdapConformance.xml")
	public void testSetRdapConformanceToDomain() {
		Domain domain = new Domain();
		Assert.notNull(domain);
		assertNull(domain.getRdapConformance());
		rdapConformanceService.initRdapConformance();
		rdapConformanceService.setRdapConformance(domain);
		assertNotNull(domain.getRdapConformance());
		assertThat(domain.getRdapConformance(),
				CoreMatchers.hasItems("rdap_level_0"));
//		assertThat(domain.getRdapConformance(),
//				CoreMatchers.hasItems("cnnic_level_0"));
	}
}
