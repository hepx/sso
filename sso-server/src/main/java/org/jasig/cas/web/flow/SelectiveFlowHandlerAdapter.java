/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.web.flow;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;
import org.springframework.webflow.mvc.servlet.FlowHandler;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;

/**
 * Selective extension of {@link FlowHandlerAdapter} that only supports {@link FlowHandler}s matching one or
 * more flow IDs.
 *
 * @author Marvin S. Addison
 * @since 4.0
 */
public class SelectiveFlowHandlerAdapter extends FlowHandlerAdapter {

    /** List of supported flow IDs. */
    @NotNull
    private List<String> supportedFlowIds;

    public void setSupportedFlowIds(final List<String> flowIdList) {
        this.supportedFlowIds = flowIdList;
    }

    public void setSupportedFlowId(final String flowId) {
        this.supportedFlowIds = Collections.singletonList(flowId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        Assert.isTrue(!supportedFlowIds.isEmpty(), "Must specify at least one supported flow ID");
    }

    @Override
    public boolean supports(final Object handler) {
        return handler instanceof FlowHandler && supportedFlowIds.contains(((FlowHandler) handler).getFlowId());
    }
}
