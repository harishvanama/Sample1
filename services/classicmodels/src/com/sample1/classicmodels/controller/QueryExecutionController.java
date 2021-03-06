/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/

package com.sample1.classicmodels.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.sample1.classicmodels.service.ClassicmodelsQueryExecutorService;
import com.sample1.classicmodels.models.query.*;

@RestController(value = "Classicmodels.QueryExecutionController")
@RequestMapping("/classicmodels/queryExecutor")
@Api(value = "QueryExecutionController", description = "controller class for query execution")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private ClassicmodelsQueryExecutorService queryService;

    @RequestMapping(value = "/queries/myquery", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "myquery")
    public Page<MyqueryResponse> executeMyquery(Pageable pageable) {
        LOGGER.debug("Executing named query: myquery");
        Page<MyqueryResponse> _result = queryService.executeMyquery(pageable);
        LOGGER.debug("got the result for named query: myquery, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query myquery")
    @RequestMapping(value = "/queries/myquery/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMyquery(@PathVariable("exportType") ExportType exportType, Pageable pageable) {
        LOGGER.debug("Exporting named query: myquery");

        return queryService.exportMyquery(exportType, pageable);
    }

}


