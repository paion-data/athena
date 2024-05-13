"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[976],{1512:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>h,contentTitle:()=>s,default:()=>d,frontMatter:()=>o,metadata:()=>r,toc:()=>l});var a=n(4848),i=n(8453);const o={title:"Getting Started",sidebar_position:1},s=void 0,r={id:"intro",title:"Getting Started",description:"[//]: # (Copyright 2024 Paion Data)",source:"@site/docs/intro.md",sourceDirName:".",slug:"/intro",permalink:"/docs/intro",draft:!1,unlisted:!1,editUrl:"https://github.com/paion-data/athena/tree/master/docs/docs/intro.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{title:"Getting Started",sidebar_position:1},sidebar:"docSidebar"},h={},l=[{value:"Docker Compose",id:"docker-compose",level:2},{value:"Extending Athena Compose",id:"extending-athena-compose",level:3},{value:"MySQL Container (Meta Store)",id:"mysql-container-meta-store",level:3},{value:"Networking in Athena Compose",id:"networking-in-athena-compose",level:3},{value:"Build From Source",id:"build-from-source",level:2},{value:"Creating a Deployable War File",id:"creating-a-deployable-war-file",level:3},{value:"Setting Up Jetty",id:"setting-up-jetty",level:3},{value:"Downloading Jetty",id:"downloading-jetty",level:4},{value:"Installing Jetty",id:"installing-jetty",level:4},{value:"Dropping the &quot;.war&quot; File into the Jetty &quot;webapp&quot;",id:"dropping-the-war-file-into-the-jetty-webapp",level:4},{value:"Starting the Webservice",id:"starting-the-webservice",level:3},{value:"Firing The First Request",id:"firing-the-first-request",level:3}];function c(e){const t={a:"a",admonition:"admonition",code:"code",em:"em",h2:"h2",h3:"h3",h4:"h4",img:"img",li:"li",ol:"ol",p:"p",pre:"pre",strong:"strong",ul:"ul",...(0,i.R)(),...e.components};return(0,a.jsxs)(a.Fragment,{children:[(0,a.jsxs)(t.p,{children:["The easiest way to get started with Athena is to use the ",(0,a.jsx)(t.a,{href:"https://github.com/paion-data/athena/tree/master/athena-examples/athena-example-books",children:"Athena Book App Starter"}),". The starter bundles all of the\ndependencies we will need to stand up a web service. This tutorial uses the starter, and all of the code is\n",(0,a.jsx)(t.a,{href:"https://github.com/paion-data/athena/tree/master/athena-examples/athena-example-books",children:"available here"}),". We will deploy and play with this example locally"]}),"\n",(0,a.jsx)(t.h2,{id:"docker-compose",children:"Docker Compose"}),"\n",(0,a.jsxs)(t.p,{children:["Athena Compose is a tool for setting up and running a full-fledged Athena instance Docker application. With Compose,\nan Athena application is backed by a real MySQL meta store and an in-memory OpenStack Swift service. With a single\ncommand, we will be able to create and start all the services from Athena. ",(0,a.jsx)(t.strong,{children:"It's the quickest approach to get a taste\nof Athena"}),"."]}),"\n",(0,a.jsxs)(t.p,{children:["Athena Compose works in all environments: production, staging, development, testing, as well as CI workflows. You can\nlearn more about it from ",(0,a.jsx)(t.a,{href:"https://github.com/paion-data/athena/tree/master/athena-examples/athena-example-books",children:"source code"}),"."]}),"\n",(0,a.jsx)(t.p,{children:"Using Athena Compose is basically a three-step process:"}),"\n",(0,a.jsxs)(t.ol,{children:["\n",(0,a.jsxs)(t.li,{children:["Package Athena at project root with ",(0,a.jsx)(t.code,{children:"mvn clean package"})]}),"\n",(0,a.jsxs)(t.li,{children:["cd into ",(0,a.jsx)(t.a,{href:"https://github.com/paion-data/athena/tree/master/athena-examples/athena-example-books",children:"compose top directory"})," and fire-up ",(0,a.jsx)(t.code,{children:"docker compose up"})]}),"\n",(0,a.jsxs)(t.li,{children:["hit Athena at ",(0,a.jsx)(t.code,{children:"http://localhost/v1/metadata/graphql?query={metaData(fileId:%221%22){fileName}}"})," with your favorite\nbrowser"]}),"\n"]}),"\n",(0,a.jsxs)(t.p,{children:["For more information about the Athena Compose the ",(0,a.jsx)(t.a,{href:"https://github.com/paion-data/athena/tree/master/athena-examples/athena-example-books",children:"Compose file definition"}),"."]}),"\n",(0,a.jsx)(t.p,{children:"Athena Compose has ability for managing the whole lifecycle of an Athena application:"}),"\n",(0,a.jsxs)(t.ul,{children:["\n",(0,a.jsx)(t.li,{children:"Start, stop, and rebuild services"}),"\n",(0,a.jsx)(t.li,{children:"View the status of running services"}),"\n",(0,a.jsx)(t.li,{children:"Stream the log output of running services"}),"\n",(0,a.jsx)(t.li,{children:"Run a one-off command on a service"}),"\n"]}),"\n",(0,a.jsx)(t.h3,{id:"extending-athena-compose",children:"Extending Athena Compose"}),"\n",(0,a.jsxs)(t.p,{children:["Happy with Athena? You can go further with productionizing Athena from\nhere ",(0,a.jsx)("img",{src:"https://user-images.githubusercontent.com/16126939/174438007-b9adae25-baf8-42a7-bf39-83786435d397.gif",width:"40"})]}),"\n",(0,a.jsx)(t.p,{children:"If you would like to go from basic Athena Compose setup and changed anything, rebuild it with"}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"docker compose up --build --force-recreate\n"})}),"\n",(0,a.jsxs)(t.p,{children:["Athena Compose has been tested with ",(0,a.jsx)(t.a,{href:"https://hub.docker.com/_/mysql",children:"MySQL 5.7"})," connected using\n",(0,a.jsx)(t.em,{children:"mysql-connector-java 5.1.38"})," within Athena running on ",(0,a.jsx)(t.a,{href:"https://hub.docker.com/_/jetty",children:"Jetty 9.3"}),"."]}),"\n",(0,a.jsx)(t.admonition,{type:"warning",children:(0,a.jsxs)(t.p,{children:["Please take extra caution with MySQL 8, as some of the features might not work properly on Athena Compose. In\naddition, make sure ",(0,a.jsx)(t.code,{children:"?autoReconnect=true&useSSL=false"})," is in connection string. For example,\n",(0,a.jsx)(t.code,{children:"jdbc:mysql://db:3306/Athena?autoReconnect=true&useSSL=false"})]})}),"\n",(0,a.jsx)(t.h3,{id:"mysql-container-meta-store",children:"MySQL Container (Meta Store)"}),"\n",(0,a.jsx)(t.p,{children:"Athena Compose uses MySQL 5 as the backing meta store, i.e. the database that DataFetcher is talking to for file\nmetadata."}),"\n",(0,a.jsx)(t.p,{children:"The MySQL instance is network-reachable at 3306 inside compose and 3305 for host (wo choose 3305 just in case 3306 has\nalready been occupied)"}),"\n",(0,a.jsx)(t.h3,{id:"networking-in-athena-compose",children:"Networking in Athena Compose"}),"\n",(0,a.jsxs)(t.p,{children:["By default Athena Compose sets up a single\n",(0,a.jsx)(t.a,{href:"https://docs.docker.com/engine/reference/commandline/network_create/",children:"network"}),". Both Athena and MySQL container\nservices join this default network and is both reachable by other containers on that network, and discoverable by them\nat a hostname identical to the container name."]}),"\n",(0,a.jsxs)(t.p,{children:["For example, inside ",(0,a.jsx)(t.a,{href:"https://github.com/paion-data/athena/tree/master/athena-examples/athena-example-books/docker-compose.yml",children:"docker-compose.yml"})]}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-yaml",children:'services:\n  web:\n    build: .\n    ports:\n      - "80:8080"\n    depends_on:\n      db:\n        condition: service_healthy\n  db:\n    image: "mysql:5.7"\n    ports:\n      - "3305:3306"\n    volumes:\n      - "./mysql-init.sql:/docker-entrypoint-initdb.d/mysql-init.sql"\n    environment:\n      MYSQL_ROOT_PASSWORD: root\n    healthcheck:\n      test: mysqladmin ping -h localhost -u root -proot\n      timeout: 3s\n      retries: 3\n'})}),"\n",(0,a.jsx)(t.p,{children:"When you run docker compose up, the following happens:"}),"\n",(0,a.jsxs)(t.ul,{children:["\n",(0,a.jsx)(t.li,{children:'A network called "athena-example-books" is created.'}),"\n",(0,a.jsx)(t.li,{children:'An Athena container is created using athena-example-books configuration. It joins the network "athena-example-books"\nunder the name "web".'}),"\n",(0,a.jsxs)(t.li,{children:["An MySQL container is created using ",(0,a.jsx)(t.code,{children:"db"}),'\'s configuration. It joins the network "athena-example-books" under the name\n"db".']}),"\n"]}),"\n",(0,a.jsxs)(t.p,{children:["Each container can now look up the hostname ",(0,a.jsx)(t.code,{children:"web"})," or ",(0,a.jsx)(t.code,{children:"db"})," and get back the appropriate container's IP address. For\nexample, web's application code could connect to the URL ",(0,a.jsx)(t.code,{children:"mysql://db:3306"})," and start using the MySQL database."]}),"\n",(0,a.jsx)(t.h2,{id:"build-from-source",children:"Build From Source"}),"\n",(0,a.jsx)(t.h3,{id:"creating-a-deployable-war-file",children:"Creating a Deployable War File"}),"\n",(0,a.jsx)(t.p,{children:'We build the ".war" File first by navigating to athena project root and compile the project'}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"cd athena/\nmvn clean package\n"})}),"\n",(0,a.jsxs)(t.p,{children:['Successfully executing the command above shall generate a ".war" file under\n',(0,a.jsx)(t.code,{children:"athena/athena-examples/athena-example-books/target/athena-example-books-<athena-version>.war"}),", where is the version of\nthe athena, for example ",(0,a.jsx)(t.code,{children:"1.0.2"}),", please make sure to replace ",(0,a.jsx)(t.code,{children:"<athena-version>"})," with one of\n",(0,a.jsx)(t.a,{href:"https://central.sonatype.com/namespace/com.paiondata.athena",children:"our release versions"}),"."]}),"\n",(0,a.jsx)(t.h3,{id:"setting-up-jetty",children:"Setting Up Jetty"}),"\n",(0,a.jsx)(t.h4,{id:"downloading-jetty",children:"Downloading Jetty"}),"\n",(0,a.jsxs)(t.p,{children:["At ",(0,a.jsx)(t.a,{href:"https://www.eclipse.org/jetty/download.php",children:"download page"}),", pick up a ",(0,a.jsx)(t.code,{children:".tgz"}),' distribution, we will use\n"9.4.44.v20210927" release as an example:']}),"\n",(0,a.jsx)(t.p,{children:(0,a.jsx)(t.img,{alt:"Error loading download-jetty.png",src:n(9777).A+"",width:"2316",height:"1266"})}),"\n",(0,a.jsx)(t.h4,{id:"installing-jetty",children:"Installing Jetty"}),"\n",(0,a.jsxs)(t.p,{children:["Put the ",(0,a.jsx)(t.code,{children:"tar.gz"})," file into a preferred location as the installation path and extract the Jetty binary using"]}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"tar -czvf jetty-distribution-9.4.44.v20210927.tar.gz\n"})}),"\n",(0,a.jsx)(t.h4,{id:"dropping-the-war-file-into-the-jetty-webapp",children:'Dropping the ".war" File into the Jetty "webapp"'}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"cd jetty-distribution-9.4.44.v20210927/webapps/\nmv /path/to/.war .\n"})}),"\n",(0,a.jsxs)(t.p,{children:['Then rename the war file to "ROOT.war", the reason of which is so that the context path would be root context - ',(0,a.jsx)(t.code,{children:"/"}),",\nwhich is a common industry standard."]}),"\n",(0,a.jsxs)(t.admonition,{title:"Setting a Context Path",type:"tip",children:[(0,a.jsxs)(t.p,{children:["The context path is the prefix of a URL path that is used to select the context(s) to which an incoming request is\npassed. Typically a URL in a Java servlet server is of the format\n",(0,a.jsx)(t.code,{children:"http://hostname.com/contextPath/servletPath/pathInfo"}),', where each of the path elements can be zero or more "/"\nseparated elements. If there is no context path, the context is referred to as the ',(0,a.jsx)(t.strong,{children:"root context"}),'. The root context\nmust be configured as "/" but is reported as the empty string by the servlet\n',(0,a.jsxs)(t.a,{href:"https://www.eclipse.org/jetty/",children:["API ",(0,a.jsx)(t.code,{children:"getContextPath()"})," method"]}),"."]}),(0,a.jsxs)(t.p,{children:["How we set the context path depends on how we deploy the web application (or ",(0,a.jsx)(t.code,{children:"ContextHandler"}),"). In this case, we\nconfigure the context path by ",(0,a.jsx)(t.strong,{children:"naming convention"}),":"]}),(0,a.jsxs)(t.p,{children:["If a web application is deployed using the WebAppProvider of the DeploymentManager without an XML IoC file, then ",(0,a.jsx)(t.strong,{children:"the\nname of the WAR file is used to set the context path"}),":"]}),(0,a.jsxs)(t.ul,{children:["\n",(0,a.jsxs)(t.li,{children:['If the WAR file is named "myapp.war", then the context will be deployed with a context path of ',(0,a.jsx)(t.code,{children:"/myapp"})]}),"\n",(0,a.jsx)(t.li,{children:(0,a.jsxs)(t.strong,{children:['If the WAR file is named "ROOT.WAR" (or any case insensitive variation), then the context will be deployed with a\ncontext path of ',(0,a.jsx)(t.code,{children:"/"})]})}),"\n",(0,a.jsxs)(t.li,{children:['If the WAR file is named "ROOT-foobar.war" (or any case insensitive variation), then the context will be deployed\nwith a context path of / and a\n',(0,a.jsx)(t.a,{href:"https://www.eclipse.org/jetty/documentation/jetty-9/index.html#configuring-virtual-hosts",children:"virtual host"}),' of "foobar"']}),"\n"]})]}),"\n",(0,a.jsx)(t.h3,{id:"starting-the-webservice",children:"Starting the Webservice"}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"cd ../\njava -jar start.jar\n"})}),"\n",(0,a.jsxs)(t.admonition,{type:"tip",children:[(0,a.jsx)(t.p,{children:"To specify the port that container exposes for our app, we could use"}),(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"java -jar start.jar -Djetty.port=8081\n"})})]}),"\n",(0,a.jsx)(t.h3,{id:"firing-the-first-request",children:"Firing The First Request"}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"brew install --cask graphiql\n"})})]})}function d(e={}){const{wrapper:t}={...(0,i.R)(),...e.components};return t?(0,a.jsx)(t,{...e,children:(0,a.jsx)(c,{...e})}):c(e)}},9777:(e,t,n)=>{n.d(t,{A:()=>a});const a=n.p+"assets/images/download-jetty-d712425d9134aad9ab839a6009a73c4e.png"}}]);