"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[299],{4511:(e,t,a)=>{a.r(t),a.d(t,{assets:()=>c,contentTitle:()=>r,default:()=>h,frontMatter:()=>i,metadata:()=>o,toc:()=>l});var n=a(4848),s=a(8453);const i={title:"Testing",sidebar_position:2},r=void 0,o={id:"contributing/test",title:"Testing",description:"[//]: # (Copyright Paion Data)",source:"@site/docs/contributing/test.md",sourceDirName:"contributing",slug:"/contributing/test",permalink:"/athena/docs/contributing/test",draft:!1,unlisted:!1,editUrl:"https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/docs/contributing/test.md",tags:[],version:"current",sidebarPosition:2,frontMatter:{title:"Testing",sidebar_position:2},sidebar:"docSidebar",previous:{title:"Basic Dependency Injection using Jersey's HK2",permalink:"/athena/docs/contributing/jersey-di-using-hk2"},next:{title:"FAQ",permalink:"/athena/docs/faq"}},c={},l=[{value:"Groovy Spock",id:"groovy-spock",level:2},{value:"Servlet Testing",id:"servlet-testing",level:2},{value:"1. Initializing ApplicationState",id:"1-initializing-applicationstate",level:3},{value:"2. Creating Test Harness",id:"2-creating-test-harness",level:3},{value:"3. Running Tests",id:"3-running-tests",level:3},{value:"4. Tearing Down Tests",id:"4-tearing-down-tests",level:3},{value:"Troubleshooting",id:"troubleshooting",level:2},{value:"Adding Custom Resources to ResourceConfig",id:"adding-custom-resources-to-resourceconfig",level:3},{value:"Database",id:"database",level:2},{value:"Reference - Apache Commons DBCP2",id:"reference---apache-commons-dbcp2",level:3},{value:"Derby",id:"derby",level:3}];function d(e){const t={a:"a",admonition:"admonition",code:"code",em:"em",h2:"h2",h3:"h3",img:"img",li:"li",ol:"ol",p:"p",pre:"pre",ul:"ul",...(0,s.R)(),...e.components};return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(t.h2,{id:"groovy-spock",children:"Groovy Spock"}),"\n",(0,n.jsxs)(t.p,{children:["We're ",(0,n.jsx)(t.em,{children:"big"})," believers in testing our code, both for correctness, as well as to ensure that changes don't unintentionally\nbreak existing contracts unintentionally. For example, we rely heavily on the ",(0,n.jsx)(t.a,{href:"http://spockframework.org/",children:"Spock"}),"\nframework for our backend service tests, and see a lot of benefit from it's conciseness, built-in\n",(0,n.jsx)(t.a,{href:"http://spockframework.org/spock/docs/1.1-rc-2/interaction_based_testing.html",children:"mocking framework"}),", and the fact that it uses ",(0,n.jsx)(t.a,{href:"http://www.groovy-lang.org/",children:"Groovy"}),"."]}),"\n",(0,n.jsxs)(t.p,{children:["We also strive for very high-quality code, with the belief that quality code is easier to maintain, easier to\nunderstand, and has fewer bugs. To help keep the quality bar high. For instance we have an automated style checker\n(",(0,n.jsx)(t.a,{href:"http://checkstyle.sourceforge.net/",children:"Checkstyle"}),") in our Maven-based projects with rules that ",(0,n.jsx)(t.em,{children:"should"})," catch most of the common style issues."]}),"\n",(0,n.jsx)(t.h2,{id:"servlet-testing",children:"Servlet Testing"}),"\n",(0,n.jsxs)(t.admonition,{type:"tip",children:[(0,n.jsxs)(t.p,{children:["The design of athena-core tests, servlet tests in particular, draws extensively from\n",(0,n.jsx)(t.a,{href:"https://github.com/yahoo/fili/blob/master/fili-core/src/test/java/com/yahoo/bard/webservice/application/JerseyTestBinder.java",children:"fili"})]}),(0,n.jsxs)(t.p,{children:["One noticeable deviation is that since some of Fili's classes have made it possible for themselves to be mutable,\nwhich Athena doesn't do, the stubbing is defined not on these classes, but on\n",(0,n.jsx)(t.a,{href:"../../../athena-core/src/test/java/io/github/paiondata/athena/application/ApplicationState.java",children:"ApplicationState"}),", which\nis a modified adaption of Fili ApplicationState"]})]}),"\n",(0,n.jsxs)(t.p,{children:[(0,n.jsx)(t.a,{href:"https://github.com/paiondata/athena/tree/master/athena-core/src/main/java/io/github/paiondata/athena/web/endpoints",children:"Servlet-related testing"}),"\nis carried out using\n",(0,n.jsx)(t.a,{href:"https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/test-framework.html",children:"Jersey Test Framework"}),"."]}),"\n",(0,n.jsx)(t.p,{children:(0,n.jsx)(t.img,{alt:"Error loading class-diagram.png",src:a(5403).A+"",width:"2762",height:"2269"})}),"\n",(0,n.jsxs)(t.p,{children:["Each\n",(0,n.jsx)(t.a,{href:"https://github.com/paiondata/athena/tree/master/athena-core/src/test/groovy/io/github/paiondata/athena/web/endpoints",children:(0,n.jsx)(t.code,{children:"***ServletSpec.groovy"})}),"\nfollows the following pattern to setup, run, and shutdown tests:"]}),"\n",(0,n.jsx)(t.h3,{id:"1-initializing-applicationstate",children:"1. Initializing ApplicationState"}),"\n",(0,n.jsxs)(t.p,{children:["Test specs initializes test data and mocking through\n",(0,n.jsx)(t.a,{href:"../../../athena-core/src/test/java/io/github/paiondata/athena/application/ApplicationState.java",children:"ApplicationState"})," in\n",(0,n.jsx)(t.code,{children:"setup()"})]}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-groovy",children:"def setup() {\n    ApplicationState applicationState = new ApplicationState();\n    applicationState.metadataByFileId = ...\n    applicationState.queryFormatter = ...\n    applicationState.mutationFormatter = ...\n\n    ...\n}\n"})}),"\n",(0,n.jsxs)(t.ul,{children:["\n",(0,n.jsxs)(t.li,{children:[(0,n.jsx)(t.code,{children:"applicationState.metadataByFileId"})," initializes GraphQL DataFetcher data"]}),"\n",(0,n.jsxs)(t.li,{children:[(0,n.jsx)(t.code,{children:"queryFormatter"})," transforms a (file ID, metadata field list) pair to a native GraphQL query"]}),"\n",(0,n.jsxs)(t.li,{children:[(0,n.jsx)(t.code,{children:"mutationFormatter"})," transforms a (file ID, metadata object) pair to a native GraphQL query that persists a new\nmetadata to database (or just in-memory that usually suffices in testing scenarios)"]}),"\n"]}),"\n",(0,n.jsx)(t.h3,{id:"2-creating-test-harness",children:"2. Creating Test Harness"}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-groovy",children:"def setup() {\n    ...\n\n    jerseyTestBinder = new JerseyTestBinder(true, applicationState, ***Servlet.class)\n}\n"})}),"\n",(0,n.jsxs)(t.p,{children:["Executing the statement above will start a ",(0,n.jsx)(t.a,{href:"https://javaee.github.io/grizzly/",children:"Grizzly container"}),". After that all Athena\nendpoints are ready to receive test requests."]}),"\n",(0,n.jsxs)(t.admonition,{type:"tip",children:[(0,n.jsxs)(t.p,{children:["When writing tests for\n",(0,n.jsx)(t.a,{href:"../../../athena-core/src/main/java/io/github/paiondata/athena/web/endpoints/FileServlet.java",children:"FileServlet"}),", make sure\n",(0,n.jsx)(t.code,{children:"MultiPartFeature.class"})," is also passed in as a resource class since the file uploading involves a separate Jersey\ncomponent enabled by it. For example:"]}),(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-java",children:"jerseyTestBinder = new BookJerseyTestBinder(true, FileServlet.class, MultiPartFeature.class)\n"})})]}),"\n",(0,n.jsxs)(t.p,{children:["The first boolean argument (",(0,n.jsx)(t.code,{children:"true"}),") is a flag to indicate whether or not, on executing the statement, servlet container\nstarts immediately. If we would like to defer the startup, change that to ",(0,n.jsx)(t.code,{children:"false"})," and manually start the container later\nby"]}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-groovy",children:"jerseyTestBinder.start()\n"})}),"\n",(0,n.jsxs)(t.p,{children:["Internally\n",(0,n.jsx)(t.a,{href:"../../../athena-core/src/test/java/io/github/paiondata/athena/application/JerseyTestBinder.java",children:"JerseyTestBinder"})," sets\n",(0,n.jsx)(t.a,{href:"../../../athena-core/src/test/java/io/github/paiondata/athena/application/TestBinderFactory.java",children:"TestBinderFactory"})," to\nbind those data and behaviors into the actual test"]}),"\n",(0,n.jsx)(t.admonition,{type:"note",children:(0,n.jsxs)(t.p,{children:["The ",(0,n.jsx)(t.a,{href:"../../../athena-core/src/test/java/io/github/paiondata/athena/application/JerseyTestBinder.java",children:"JerseyTestBinder"}),"\ncreates separate container for each test. Setup method is named ",(0,n.jsx)(t.code,{children:"setup()"})," and teardown method ",(0,n.jsx)(t.code,{children:"cleanup()"})," by Groovy\nSpock convention."]})}),"\n",(0,n.jsx)(t.h3,{id:"3-running-tests",children:"3. Running Tests"}),"\n",(0,n.jsxs)(t.p,{children:["To send test request in order to test endpoints, use ",(0,n.jsx)(t.code,{children:"JerseyTestBinder.makeRequest"})," method, which returns a native\njavax rs ws request object:"]}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-groovy",children:'def "File meta data can be accessed through GraphQL GET endpoint"() {\n    when: "we get meta data via GraphQL GET"\n    String actual = jerseyTestBinder.makeRequest(\n            "/metadata/graphql",\n            [query: URLEncoder.encode("""{metaData(fileId:"$FILE_ID"){fileName\\nfileType}}""", "UTF-8")]\n    ).get(String.class)\n\n    then: "the response contains all requested metadata info without error"\n    new JsonSlurper().parseText(actual) == new JsonSlurper().parseText(expectedMultiFieldMetadataResponse())\n}\n'})}),"\n",(0,n.jsx)(t.h3,{id:"4-tearing-down-tests",children:"4. Tearing Down Tests"}),"\n",(0,n.jsxs)(t.p,{children:["The teardown shuts down test container as well as cleaning up all ApplicationStates we defined in\n",(0,n.jsx)(t.a,{href:"#1-initializing-applicationstate",children:"step 1"})]}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-groovy",children:"def cleanup() {\n    // Release the test web container\n    jerseyTestBinder.tearDown()\n}\n"})}),"\n",(0,n.jsx)(t.h2,{id:"troubleshooting",children:"Troubleshooting"}),"\n",(0,n.jsx)(t.h3,{id:"adding-custom-resources-to-resourceconfig",children:"Adding Custom Resources to ResourceConfig"}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-bash",children:"java.lang.IllegalStateException: org.glassfish.jersey.server.model.ModelValidationException: Validation of the\napplication resource model has failed during application initialization.\n[FATAL] No injection source found for a parameter of type public javax.ws.rs.core.Response\n\n...\n\nCaused by: org.glassfish.jersey.server.model.ModelValidationException: Validation of the application resource model has\nfailed during application initialization.\n"})}),"\n",(0,n.jsxs)(t.p,{children:["Athena uses ResourceConfig type for configuration. We need to register the ",(0,n.jsx)(t.code,{children:"MultiPartFeature"}),". Instead of using\n",(0,n.jsx)(t.a,{href:"../../../athena-core/src/main/java/io/github/paiondata/athena/application/ResourceConfig.java",children:"Athena ResourceConfig"}),",\nservlet test spec configures with the native\n",(0,n.jsx)(t.a,{href:"https://github.com/eclipse-ee4j/jersey/blob/master/core-server/src/main/java/org/glassfish/jersey/server/ResourceConfig.java",children:"Jersey ResourceConfig"}),".\nThe reason is so that we could bind certain resource classes that we only need in a test spec to enhance test\nefficiency."]}),"\n",(0,n.jsx)(t.p,{children:"Athena ResourceConfig registers MultiPartFeature by default, whereas Jersey ResourceConfig does not. We could register\nthis resource as an extra resource class using"}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-groovy",children:"jerseyTestBinder = new JerseyTestBinder(true, applicationState, FileServlet.class, MultiPartFeature.class)\n"})}),"\n",(0,n.jsxs)(t.p,{children:["Note that along with the ",(0,n.jsx)(t.code,{children:"FileServlet"})," resource that's going to be registered and tested, ",(0,n.jsx)(t.code,{children:"MultiPartFeature"})," will also\ngot registered by Jersey ResourceConfig."]}),"\n",(0,n.jsx)(t.h2,{id:"database",children:"Database"}),"\n",(0,n.jsx)(t.p,{children:"Database-related tests contain 2 parts"}),"\n",(0,n.jsxs)(t.ol,{children:["\n",(0,n.jsxs)(t.li,{children:["\n",(0,n.jsx)(t.p,{children:"Groovy Spock unit tests on"}),"\n",(0,n.jsxs)(t.ul,{children:["\n",(0,n.jsx)(t.li,{children:(0,n.jsx)(t.a,{href:"../../../athena-examples/athena-example-books/src/test/groovy/io/github/paiondata/athena/example/books/application/SQLQueryDataFetcherSpec.groovy",children:"Injected Query DataFetcher"})}),"\n",(0,n.jsx)(t.li,{children:(0,n.jsx)(t.a,{href:"../../../athena-examples/athena-example-books/src/test/groovy/io/github/paiondata/athena/example/books/application/SQLMutationDataFetcherSpec.groovy",children:"Injected Mutation DataFetcher"})}),"\n"]}),"\n"]}),"\n",(0,n.jsxs)(t.li,{children:["\n",(0,n.jsx)(t.p,{children:"Live DB tests on endpoints"}),"\n",(0,n.jsxs)(t.ul,{children:["\n",(0,n.jsxs)(t.li,{children:["In ",(0,n.jsx)(t.a,{href:"../../../athena-examples/athena-example-books/src/test/groovy/io/github/paiondata/athena/example/books/web/endpoints/FileServletSpec.groovy",children:"file servlet endpoint test"}),"\nand\n",(0,n.jsx)(t.a,{href:"../../../athena-examples/athena-example-books/src/test/groovy/io/github/paiondata/athena/example/books/web/endpoints/MetaServletSpec.groovy",children:"meta data servlet endpoint test"}),",\n",(0,n.jsx)(t.a,{href:"../../../athena-examples/athena-example-books/src/test/groovy/io/github/paiondata/athena/example/books/application/SQLDBResourceManager.groovy",children:"Flyway migration"}),"\ninjects real data into a Derby in-meomroy SQL DB"]}),"\n",(0,n.jsxs)(t.li,{children:["The Derby data is injected via a shared ",(0,n.jsx)(t.a,{href:"#reference---apache-commons-dbcp2",children:"DBCP DataSource"})," declared in\n",(0,n.jsx)(t.a,{href:"../../../athena-examples/athena-example-books/src/main/java/io/github/paiondata/athena/example/books/application/BooksBinderFactory.java",children:"application BinderFactory"})]}),"\n",(0,n.jsxs)(t.li,{children:["The application resource is set alive through\n",(0,n.jsx)(t.a,{href:"../../../athena-examples/athena-example-books/src/test/java/io/github/paiondata/athena/example/books/application/BookJerseyTestBinder.java",children:"JerseyTestBinder"})]}),"\n"]}),"\n"]}),"\n"]}),"\n",(0,n.jsx)(t.h3,{id:"reference---apache-commons-dbcp2",children:"Reference - Apache Commons DBCP2"}),"\n",(0,n.jsx)(t.p,{children:"For testing Book example application, we use Derby's in-memory database facility, which resides completely in main\nmemory, not in the file system."}),"\n",(0,n.jsxs)(t.p,{children:["In addition, we use ",(0,n.jsx)(t.a,{href:"https://commons.apache.org/proper/commons-dbcp/",children:"Apache DBCP 2"})," to provide\n",(0,n.jsx)(t.a,{href:"https://gitbox.apache.org/repos/asf?p=commons-dbcp.git;a=blob_plain;f=doc/BasicDataSourceExample.java;hb=HEAD",children:"DataSource"}),"\npointing at the in-memory Derby instance."]}),"\n",(0,n.jsx)(t.h3,{id:"derby",children:"Derby"}),"\n",(0,n.jsx)(t.p,{children:"Derby was meant to be used only in tests and, hence, must be imported in test scope only"})]})}function h(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,n.jsx)(t,{...e,children:(0,n.jsx)(d,{...e})}):d(e)}},5403:(e,t,a)=>{a.d(t,{A:()=>n});const n=a.p+"assets/images/class-diagram-a5c1c8daba1cd5ba117b1da00b3f716a.png"},8453:(e,t,a)=>{a.d(t,{R:()=>r,x:()=>o});var n=a(6540);const s={},i=n.createContext(s);function r(e){const t=n.useContext(i);return n.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function o(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:r(e.components),n.createElement(i.Provider,{value:t},e.children)}}}]);