"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[850],{7122:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>l,contentTitle:()=>a,default:()=>h,frontMatter:()=>s,metadata:()=>o,toc:()=>d});var i=t(4848),r=t(8453);const s={title:"Development",sidebar_position:1},a=void 0,o={id:"contributing/development",title:"Development",description:"[//]: # (Copyright 2024 Paion Data)",source:"@site/docs/contributing/development.md",sourceDirName:"contributing",slug:"/contributing/development",permalink:"/athena/docs/contributing/development",draft:!1,unlisted:!1,editUrl:"https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/docs/contributing/development.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{title:"Development",sidebar_position:1},sidebar:"docSidebar",previous:{title:"Contributor Guide",permalink:"/athena/docs/category/contributor-guide"},next:{title:"Basic Dependency Injection using Jersey's HK2",permalink:"/athena/docs/contributing/jersey-di-using-hk2"}},l={},d=[{value:"Overview",id:"overview",level:2},{value:"Building",id:"building",level:2},{value:"Running Webservice in Standalone Jetty",id:"running-webservice-in-standalone-jetty",level:2},{value:"Download Jetty",id:"download-jetty",level:3},{value:"Setting Up Standalone Jetty",id:"setting-up-standalone-jetty",level:3},{value:"Running Webservice",id:"running-webservice",level:3},{value:"Release Versions",id:"release-versions",level:2}];function c(e){const n={a:"a",code:"code",em:"em",h2:"h2",h3:"h3",img:"img",li:"li",ol:"ol",p:"p",pre:"pre",strong:"strong",...(0,r.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsxs)(n.p,{children:["Athena is developed in ",(0,i.jsx)(n.a,{href:"https://eclipse-ee4j.github.io/jersey/",children:"Jersey"})," framework."]}),"\n",(0,i.jsxs)(n.p,{children:[(0,i.jsx)(n.strong,{children:"NOTE:"}),' In case you are not familiar with Jersey, it is a parallel technology with "Spring Boot framework". ',(0,i.jsx)(n.strong,{children:"Athena\noffers absolutely NO support for Spring"})," and will remain as an exclusive Jersey application in the future, because\nJersey, alone with its backing technology ",(0,i.jsx)(n.a,{href:"https://javaee.github.io/hk2/",children:"HK2"}),", is the reference-implementation of\nJSR-370 (and HK2, JSR-330) ",(0,i.jsx)(n.em,{children:"standards"})," while Spring is not."]}),"\n",(0,i.jsx)(n.p,{children:'By "having no support for Spring", Athena means the following:'}),"\n",(0,i.jsxs)(n.ol,{children:["\n",(0,i.jsx)(n.li,{children:"Athena DOES NOT, AND WILL NOT, run as a Spring Boot Webservice"}),"\n",(0,i.jsx)(n.li,{children:"Athena has ABSOLUTE ZERO direct-dependency from Spring"}),"\n",(0,i.jsx)(n.li,{children:"Athena runs in NON-SPRING containers, such as Jetty"}),"\n"]}),"\n",(0,i.jsxs)(n.p,{children:[(0,i.jsx)(n.em,{children:"Athena rejects any conducts that violate the 3 rules above. NO EXCEPTION"}),"."]}),"\n",(0,i.jsx)(n.h2,{id:"overview",children:"Overview"}),"\n",(0,i.jsx)(n.p,{children:"The following guide is intended to help developers who maintain or want to make changes to the Athena framework."}),"\n",(0,i.jsx)(n.h2,{id:"building",children:"Building"}),"\n",(0,i.jsx)(n.p,{children:"Athena is built using Maven. Because Athena is a mono-repo with interdependencies between modules, it is recommended to\nfully build and install the project at least once:"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"mvn clean install\n"})}),"\n",(0,i.jsx)(n.p,{children:"Thereafter, individual modules can be built whenever making changes to them. For example, the following command would\nrebuild only athena-core:"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"mvn clean install -f athena-core\n"})}),"\n",(0,i.jsx)(n.p,{children:"Pull requests and release builds leverage GitHub Action. PR builds simply run the complete build along with code\ncoverage."}),"\n",(0,i.jsx)(n.h2,{id:"running-webservice-in-standalone-jetty",children:"Running Webservice in Standalone Jetty"}),"\n",(0,i.jsx)(n.h3,{id:"download-jetty",children:"Download Jetty"}),"\n",(0,i.jsxs)(n.p,{children:["For JDK ",(0,i.jsx)(n.strong,{children:"17"}),", which is the version JWT runs on, it's been tested that Jetty ",(0,i.jsx)(n.em,{children:"11.0.15"})," worked. Hence, we will use\n",(0,i.jsx)(n.a,{href:"https://repo1.maven.org/maven2/org/eclipse/jetty/jetty-home/11.0.15/jetty-home-11.0.15.tar.gz",children:'"11.0.15" release'})," as\nan example:"]}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsx)(n.img,{alt:"Error loading download-jetty.png",src:t(2792).A+"",width:"3584",height:"1878"})}),"\n",(0,i.jsxs)(n.p,{children:["Put the ",(0,i.jsx)(n.code,{children:"tar.gz"})," file into a location of your choice as the installation path and extract the Jetty binary using"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"tar -xzvf jetty-home-11.0.15.tar.gz\n"})}),"\n",(0,i.jsxs)(n.p,{children:["The extracted directory ",(0,i.jsx)(n.em,{children:"jetty-home-11.0.15"})," is the Jetty distribution. We call this directory ",(0,i.jsx)(n.strong,{children:"$JETTY_HOME"}),", which\nshould not be modified."]}),"\n",(0,i.jsx)(n.h3,{id:"setting-up-standalone-jetty",children:"Setting Up Standalone Jetty"}),"\n",(0,i.jsxs)(n.p,{children:["Our ",(0,i.jsx)(n.a,{href:"#building",children:"WAR file"})," will be dropped to a directory where Jetty can pick up and run. We call this directory\n",(0,i.jsx)(n.strong,{children:"$JETTY_BASE"}),", which is usually different from the ",(0,i.jsx)(n.em,{children:"$JETTY_HOME"}),". The ",(0,i.jsx)(n.em,{children:"$JETTY_BASE"})," also contains container runtime\nconfigs. In short, the Standalone Jetty container will be setup with"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"export JETTY_HOME=/path/to/jetty-home-11.0.15\nmkdir -p /path/to/jetty-base\ncd /path/to/jetty-base\njava -jar $JETTY_HOME/start.jar --add-module=annotations,server,http,deploy,servlet,webapp,resources,jsp\n"})}),"\n",(0,i.jsxs)(n.p,{children:["where ",(0,i.jsx)(n.code,{children:"/path/to/"})," is the ",(0,i.jsx)(n.em,{children:"absolute"})," path to the directory containing the ",(0,i.jsx)(n.code,{children:"jetty-home-11.0.15"})," directory"]}),"\n",(0,i.jsxs)(n.p,{children:["The ",(0,i.jsx)(n.code,{children:"--add-module=annotations,server,http,deploy,servlet,webapp,resources,jsp"})," is how we configure the Jetty\ncontainer."]}),"\n",(0,i.jsxs)(n.p,{children:["Lastly, drop the ",(0,i.jsx)(n.a,{href:"#building",children:"WAR file"})," into ",(0,i.jsx)(n.strong,{children:"/path/to/jetty-base/webapps"})," directory and rename the WAR file to\n",(0,i.jsx)(n.strong,{children:"ROOT.war"}),":"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"mv /path/to/war-file /path/to/jetty-base/webapps/ROOT.war\n"})}),"\n",(0,i.jsx)(n.h3,{id:"running-webservice",children:"Running Webservice"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"java -jar $JETTY_HOME/start.jar\n"})}),"\n",(0,i.jsxs)(n.p,{children:["The webservice will run on port ",(0,i.jsx)(n.strong,{children:"8080"}),", and you will see the data you inserted"]}),"\n",(0,i.jsx)(n.h2,{id:"release-versions",children:"Release Versions"}),"\n",(0,i.jsxs)(n.p,{children:["Athena follows ",(0,i.jsx)(n.a,{href:"https://semver.org/",children:"semantic versioning"})," for its releases. Minor and patch versions only have the\nversion components of ",(0,i.jsx)(n.code,{children:"MAJOR.MINOR.PATCH"}),"."]}),"\n",(0,i.jsxs)(n.p,{children:["Major releases are often pre-released prior to the publication of the final version.  Pre-releases have the format of\n",(0,i.jsx)(n.code,{children:"MAJOR.MINOR.PATCH-prCANDIDATE"}),".  For example, 5.0.0-pr2 is release candidate 2 of the Athena 5.0.0 version."]})]})}function h(e={}){const{wrapper:n}={...(0,r.R)(),...e.components};return n?(0,i.jsx)(n,{...e,children:(0,i.jsx)(c,{...e})}):c(e)}},2792:(e,n,t)=>{t.d(n,{A:()=>i});const i=t.p+"assets/images/download-jetty-edc103f9f296011d2a6f18b625bff490.png"},8453:(e,n,t)=>{t.d(n,{R:()=>a,x:()=>o});var i=t(6540);const r={},s=i.createContext(r);function a(e){const n=i.useContext(s);return i.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function o(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(r):e.components||r:a(e.components),i.createElement(s.Provider,{value:n},e.children)}}}]);