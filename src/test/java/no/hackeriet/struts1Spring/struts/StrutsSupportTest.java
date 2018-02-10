/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.hackeriet.struts1Spring.struts;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.StaticWebApplicationContext;

import javax.servlet.ServletContext;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Juergen Hoeller
 * @author Alexander Kjäll
 * @since 09.04.2004
 */
public class StrutsSupportTest {
    @Test
    public void actionSupportWithContextLoaderPlugIn() {
        StaticWebApplicationContext wac = new StaticWebApplicationContext();
        wac.addMessage("test", Locale.getDefault(), "testmessage");
        final ServletContext servletContext = new MockServletContext();
        wac.setServletContext(servletContext);
        wac.refresh();
        servletContext.setAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX, wac);

        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        ActionSupport action = new ActionSupport() {
        };
        action.setServlet(actionServlet);

        assertEquals(wac, action.getWebApplicationContext());
        assertEquals(servletContext, action.getServletContext());
        assertEquals("testmessage", action.getMessageSourceAccessor().getMessage("test"));

        action.setServlet(null);
    }

    @Test
    public void actionSupportWithRootContext() {
        StaticWebApplicationContext wac = new StaticWebApplicationContext();
        wac.addMessage("test", Locale.getDefault(), "testmessage");
        final ServletContext servletContext = new MockServletContext();
        wac.setServletContext(servletContext);
        wac.refresh();
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        ActionSupport action = new ActionSupport() {
        };
        action.setServlet(actionServlet);

        assertEquals(wac, action.getWebApplicationContext());
        assertEquals(servletContext, action.getServletContext());
        assertEquals("testmessage", action.getMessageSourceAccessor().getMessage("test"));

        action.setServlet(null);
    }

    @Test
    public void dispatchActionSupportWithContextLoaderPlugIn() {
        StaticWebApplicationContext wac = new StaticWebApplicationContext();
        wac.addMessage("test", Locale.getDefault(), "testmessage");
        final ServletContext servletContext = new MockServletContext();
        wac.setServletContext(servletContext);
        wac.refresh();
        servletContext.setAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX, wac);

        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        DispatchActionSupport action = new DispatchActionSupport() {
        };
        action.setServlet(actionServlet);

        assertEquals(wac, action.getWebApplicationContext());
        assertEquals(servletContext, action.getServletContext());
        assertEquals("testmessage", action.getMessageSourceAccessor().getMessage("test"));

        action.setServlet(null);
    }

    @Test
    public void dispatchActionSupportWithRootContext() {
        StaticWebApplicationContext wac = new StaticWebApplicationContext();
        wac.addMessage("test", Locale.getDefault(), "testmessage");
        final ServletContext servletContext = new MockServletContext();
        wac.setServletContext(servletContext);
        wac.refresh();
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        DispatchActionSupport action = new DispatchActionSupport() {
        };
        action.setServlet(actionServlet);

        assertEquals(wac, action.getWebApplicationContext());
        assertEquals(servletContext, action.getServletContext());
        assertEquals("testmessage", action.getMessageSourceAccessor().getMessage("test"));

        action.setServlet(null);
    }

    @Test
    public void lookupDispatchActionSupportWithContextLoaderPlugIn() {
        StaticWebApplicationContext wac = new StaticWebApplicationContext();
        wac.addMessage("test", Locale.getDefault(), "testmessage");
        final ServletContext servletContext = new MockServletContext();
        wac.setServletContext(servletContext);
        wac.refresh();
        servletContext.setAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX, wac);

        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        LookupDispatchActionSupport action = new LookupDispatchActionSupport() {
            @Override
            protected Map getKeyMethodMap() {
                return new HashMap();
            }
        };
        action.setServlet(actionServlet);

        assertEquals(wac, action.getWebApplicationContext());
        assertEquals(servletContext, action.getServletContext());
        assertEquals("testmessage", action.getMessageSourceAccessor().getMessage("test"));

        action.setServlet(null);
    }

    @Test
    public void lookupDispatchActionSupportWithRootContext() {
        StaticWebApplicationContext wac = new StaticWebApplicationContext();
        wac.addMessage("test", Locale.getDefault(), "testmessage");
        final ServletContext servletContext = new MockServletContext();
        wac.setServletContext(servletContext);
        wac.refresh();
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        LookupDispatchActionSupport action = new LookupDispatchActionSupport() {
            @Override
            protected Map getKeyMethodMap() {
                return new HashMap();
            }
        };
        action.setServlet(actionServlet);

        assertEquals(wac, action.getWebApplicationContext());
        assertEquals(servletContext, action.getServletContext());
        assertEquals("testmessage", action.getMessageSourceAccessor().getMessage("test"));

        action.setServlet(null);
    }

    @Test
    public void testDelegatingActionProxy() throws Exception {
        final MockServletContext servletContext = new MockServletContext("/org/springframework/web/struts/");
        ContextLoaderPlugIn plugin = new ContextLoaderPlugIn();
        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public String getServletName() {
                return "action";
            }

            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };

        ModuleConfig moduleConfig = mock(ModuleConfig.class);
        when(moduleConfig.getPrefix()).thenReturn("");

        plugin.init(actionServlet, moduleConfig);
        assertTrue(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX) != null);

        DelegatingActionProxy proxy = new DelegatingActionProxy();
        proxy.setServlet(actionServlet);
        ActionMapping mapping = new ActionMapping();
        mapping.setPath("/test");
        mapping.setModuleConfig(moduleConfig);
        ActionForward forward = proxy.execute(
                mapping, null, new MockHttpServletRequest(servletContext), new MockHttpServletResponse());
        assertEquals("/test", forward.getPath());

        TestAction testAction = (TestAction) plugin.getWebApplicationContext().getBean("/test");
        assertTrue(testAction.getServlet() != null);
        proxy.setServlet(null);
        plugin.destroy();
        assertTrue(testAction.getServlet() == null);
    }

    @Test
    public void delegatingActionProxyWithModule() throws Exception {
        final MockServletContext servletContext = new MockServletContext("/org/springframework/web/struts/WEB-INF");
        ContextLoaderPlugIn plugin = new ContextLoaderPlugIn();
        plugin.setContextConfigLocation("action-servlet.xml");
        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public String getServletName() {
                return "action";
            }

            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };

        ModuleConfig moduleConfig = mock(ModuleConfig.class);
        when(moduleConfig.getPrefix()).thenReturn("/module");

        plugin.init(actionServlet, moduleConfig);
        assertTrue(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX) == null);
        assertTrue(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX + "/module") != null);

        DelegatingActionProxy proxy = new DelegatingActionProxy();
        proxy.setServlet(actionServlet);
        ActionMapping mapping = new ActionMapping();
        mapping.setPath("/test2");
        mapping.setModuleConfig(moduleConfig);
        ActionForward forward = proxy.execute(
                mapping, null, new MockHttpServletRequest(servletContext), new MockHttpServletResponse());
        assertEquals("/module/test2", forward.getPath());

        TestAction testAction = (TestAction) plugin.getWebApplicationContext().getBean("/module/test2");
        assertTrue(testAction.getServlet() != null);
        proxy.setServlet(null);
        plugin.destroy();
        assertTrue(testAction.getServlet() == null);

        verify(moduleConfig);
    }

    @Test
    public void delegatingActionProxyWithModuleAndDefaultContext() throws Exception {
        final MockServletContext servletContext = new MockServletContext("/org/springframework/web/struts/WEB-INF");
        ContextLoaderPlugIn plugin = new ContextLoaderPlugIn();
        plugin.setContextConfigLocation("action-servlet.xml");
        ActionServlet actionServlet = new ActionServlet() {
            @Override
            public String getServletName() {
                return "action";
            }

            @Override
            public ServletContext getServletContext() {
                return servletContext;
            }
        };

        ModuleConfig defaultModuleConfig = mock(ModuleConfig.class);
        when(defaultModuleConfig.getPrefix()).thenReturn("");

        ModuleConfig moduleConfig = mock(ModuleConfig.class);
        when(moduleConfig.getPrefix()).thenReturn("/module");


        plugin.init(actionServlet, defaultModuleConfig);
        assertTrue(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX) != null);
        assertTrue(servletContext.getAttribute(ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX + "/module") == null);

        DelegatingActionProxy proxy = new DelegatingActionProxy();
        proxy.setServlet(actionServlet);
        ActionMapping mapping = new ActionMapping();
        mapping.setPath("/test2");
        mapping.setModuleConfig(moduleConfig);
        ActionForward forward = proxy.execute(
                mapping, null, new MockHttpServletRequest(servletContext), new MockHttpServletResponse());
        assertEquals("/module/test2", forward.getPath());

        TestAction testAction = (TestAction) plugin.getWebApplicationContext().getBean("/module/test2");
        assertTrue(testAction.getServlet() != null);
        proxy.setServlet(null);
        plugin.destroy();
        assertTrue(testAction.getServlet() == null);
    }
}
