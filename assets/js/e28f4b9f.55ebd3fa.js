"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[779],{7946:(e,n,i)=>{i.r(n),i.d(n,{assets:()=>a,contentTitle:()=>c,default:()=>h,frontMatter:()=>r,metadata:()=>o,toc:()=>d});var t=i(4848),s=i(8453);const r={title:"Basic Dependency Injection using Jersey's HK2",sidebar_position:2},c=void 0,o={id:"contributing/jersey-di-using-hk2",title:"Basic Dependency Injection using Jersey's HK2",description:"[//]: # (Copyright 2024 Paion Data)",source:"@site/docs/contributing/jersey-di-using-hk2.md",sourceDirName:"contributing",slug:"/contributing/jersey-di-using-hk2",permalink:"/athena/docs/contributing/jersey-di-using-hk2",draft:!1,unlisted:!1,editUrl:"https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/docs/contributing/jersey-di-using-hk2.md",tags:[],version:"current",sidebarPosition:2,frontMatter:{title:"Basic Dependency Injection using Jersey's HK2",sidebar_position:2},sidebar:"docSidebar",previous:{title:"Development",permalink:"/athena/docs/contributing/development"},next:{title:"Testing",permalink:"/athena/docs/contributing/test"}},a={},d=[];function l(e){const n={a:"a",blockquote:"blockquote",code:"code",em:"em",p:"p",pre:"pre",strong:"strong",...(0,s.R)(),...e.components};return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsxs)(n.p,{children:["Jersey uses ",(0,t.jsx)(n.a,{href:"https://eclipse-ee4j.github.io/glassfish-hk2/",children:"HK2"})," as its dependency injection (DI) system. We can use other\ninjection systems, but its infrastructure is built with HK2, and allows us to also use it within our applications."]}),"\n",(0,t.jsx)(n.p,{children:"Setting up simple dependency injection with Jersey takes just a few lines of code. Let say for example we have a service\nwe would like to inject into our resources."}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:'public class GreetingService {\n\n    public String getGreeting(String name) {\n        return "Hello " + name + "!";\n    }\n}\n'})}),"\n",(0,t.jsx)(n.p,{children:"And we want to inject this service into a Jersey resource"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:'@Path("greeting")\npublic class GreetingResource {\n\n    @Inject\n    public GreetingService greetingService;\n\n    @GET\n    public String get(@QueryParam("name") String name) {\n        return this.greetingService.getGreeting(name);\n    }\n}\n'})}),"\n",(0,t.jsx)(n.p,{children:"In order for the injection to work, all we need is a simple configuration"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:'@ApplicationPath("/api")\npublic class AppConfig extends ResourceConfig {\n\n    public AppConfig() {\n        register(GreetingResource.class);\n        register(new AbstractBinder() {\n            @Override\n            protected void configure() {\n                bindAsContract(GreetingService.class);\n            }\n        });\n    }\n}\n'})}),"\n",(0,t.jsxs)(n.p,{children:["Here we are saying that we want to bind the ",(0,t.jsx)(n.code,{children:"GreetingService"})," to the injection system, and advertise it as injectable by\nthe same class. What the last statement means is that we can only inject it as ",(0,t.jsx)(n.code,{children:"GreetingService"})," and (probably\nobviously) not by any other class. As you will see later, it is possible to change this."]}),"\n",(0,t.jsxs)(n.blockquote,{children:["\n",(0,t.jsx)(n.p,{children:"Note: The injection above is field injection, where the service is injected into the field of the resource. Another\ntype of injection is constructor injection, where the service is injected into the constructor. Athena uses\nconstructor injection ubiquitously. An example of the constructor injection is shown below:"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:"private final GreetingService greetingService;\n\n@Inject\npublic GreetingResource(GreetingService greetingService) {\nthis.greetingService = greetingService;\n}\n"})}),"\n",(0,t.jsxs)(n.p,{children:["Athena chooses constructor injection as opposed to field injection, as it ",(0,t.jsx)(n.em,{children:"makes the resource easier to unit test"}),".\nConstructor injection doesn't require any different configuration."]}),"\n"]}),"\n",(0,t.jsxs)(n.p,{children:["Lets say that instead of a class, the ",(0,t.jsx)(n.code,{children:"GreetingService"})," is an interface, and we have an implementation of it (which is\nvery common). To configure that, we would use the following syntax in the above ",(0,t.jsx)(n.code,{children:"configure"})," method"]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:"@Override\nprotected void configure() {\n    bind(NiceGreetingService.class).to(GreetingService.class);\n}\n"})}),"\n",(0,t.jsxs)(n.p,{children:['This reads as "bind ',(0,t.jsx)(n.code,{children:"NiceGreetingService"}),", and advertise it as ",(0,t.jsx)(n.code,{children:"GreetingService"}),'". This means we can use the exact same\ncode in the ',(0,t.jsx)(n.code,{children:"GreetingResource"})," above, because we advertise the contract as ",(0,t.jsx)(n.code,{children:"GreetingService"})," and not\n",(0,t.jsx)(n.code,{children:"NiceGreetingService"}),". But the actual implementation, when injected, will be the ",(0,t.jsx)(n.code,{children:"NiceGreetingService"}),"."]}),"\n",(0,t.jsxs)(n.p,{children:["If you've ever worked with any injection framework, you will have came across the concept of ",(0,t.jsx)(n.strong,{children:"scope"}),', which determines\nthe lifespan of the service. You may have heard of a "Request Scope", where the service is alive only for the life of the\nrequest. Or a "Singleton Scope", where there is only one instance of the service. We can configure these scopes also\nusing the following syntax.']}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:"@Override\nprotected void configure() {\n    bind(NiceGreetingService.class)\n            .to(GreetingService.class)\n            .in(RequestScoped.class);\n}\n"})}),"\n",(0,t.jsxs)(n.p,{children:["The default scope is ",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"PerLookup"})}),", which means that every time this service is requested, a new one will be created.\nIn the example above, using the ",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"RequestScoped"})}),", a new service will be created for a single request. This may or may\nnot be the same as the ",(0,t.jsx)(n.code,{children:"PerLookup"}),", depending on how many places we are trying to inject it. We may be trying to inject\nit into a filter and into a resource. If this were ",(0,t.jsx)(n.code,{children:"PerLookup"}),", then two instances would be created for each request. In\nthis case, we only want one."]}),"\n",(0,t.jsxs)(n.p,{children:["The other two scopes available are ",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"Singleton"})})," (only one instance created) and ",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"Immediate"})})," (like Singleton) but is\ncreated on startup (whereas with ",(0,t.jsx)(n.code,{children:"Singleton"}),", it's not created until the first request)."]}),"\n",(0,t.jsxs)(n.p,{children:["Aside from binding classes, we could also just use an instance. This would gives us a ",(0,t.jsx)(n.em,{children:"default singleton"}),", so we don't\nneed to use the ",(0,t.jsx)(n.code,{children:"in"})," syntax."]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:"@Override\nprotected void configure() {\n    bind(new NiceGreetingService())\n            .to(GreetingService.class);\n}\n"})}),"\n",(0,t.jsx)(n.p,{children:"What if we have some complex creation logic or need some request context information for the service. In this case there\nare Factorys. For example"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:'public class GreetingServiceFactory implements Factory<GreetingService> {\n\n    @Context\n    UriInfo uriInfo;\n\n    @Override\n    public GreetingService provide() {\n        return new GreetingService(\n                uriInfo.getQueryParameters().getFirst("name"));\n    }\n\n    @Override\n    public void dispose(GreetingService service) {\n        /* noop */\n    }\n}\n'})}),"\n",(0,t.jsxs)(n.p,{children:["Here we have a factory, that gets request information from the ",(0,t.jsx)(n.code,{children:"UriInfo"}),", in this case a query parameters, and we create\nthe ",(0,t.jsx)(n.code,{children:"GreetingService"})," from it. To configure it, we use the following syntax"]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-java",children:"@Override\nprotected void configure() {\n    bindFactory(GreetingServiceFactory.class)\n            .to(GreetingService.class)\n            .in(RequestScoped.class);\n}\n"})})]})}function h(e={}){const{wrapper:n}={...(0,s.R)(),...e.components};return n?(0,t.jsx)(n,{...e,children:(0,t.jsx)(l,{...e})}):l(e)}},8453:(e,n,i)=>{i.d(n,{R:()=>c,x:()=>o});var t=i(6540);const s={},r=t.createContext(s);function c(e){const n=t.useContext(r);return t.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function o(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:c(e.components),t.createElement(r.Provider,{value:n},e.children)}}}]);