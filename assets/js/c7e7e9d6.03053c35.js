"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[273],{7142:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>r,contentTitle:()=>o,default:()=>h,frontMatter:()=>l,metadata:()=>a,toc:()=>c});var s=n(4848),i=n(8453);const l={title:"Spinning Up A Local Swift Instance",sidebar_position:1},o=void 0,a={id:"filestores/local-swift",title:"Spinning Up A Local Swift Instance",description:"[//]: # (Copyright 2024 Paion Data)",source:"@site/docs/filestores/local-swift.md",sourceDirName:"filestores",slug:"/filestores/local-swift",permalink:"/docs/filestores/local-swift",draft:!1,unlisted:!1,editUrl:"https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/docs/filestores/local-swift.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{title:"Spinning Up A Local Swift Instance",sidebar_position:1},sidebar:"docSidebar",previous:{title:"File Stores",permalink:"/docs/filestore"}},r={},c=[{value:"Prerequisites",id:"prerequisites",level:2},{value:"Getting started",id:"getting-started",level:2},{value:"Setup Volume for Swift",id:"setup-volume-for-swift",level:2},{value:"Start up Container Instance",id:"start-up-container-instance",level:2},{value:"Setup Swift Client",id:"setup-swift-client",level:2},{value:"Create, Upload, and Download a Test File",id:"create-upload-and-download-a-test-file",level:2}];function d(e){const t={a:"a",blockquote:"blockquote",code:"code",h2:"h2",li:"li",p:"p",pre:"pre",ul:"ul",...(0,i.R)(),...e.components};return(0,s.jsxs)(s.Fragment,{children:[(0,s.jsxs)(t.p,{children:["In this quickstart, we will download a ",(0,s.jsx)(t.a,{href:"https://hub.docker.com/r/fnndsc/docker-swift-onlyone",children:"OpenStack Swift Image"}),",\nspinup a container on a single machine, upload a test file, and download that file."]}),"\n",(0,s.jsx)(t.h2,{id:"prerequisites",children:"Prerequisites"}),"\n",(0,s.jsx)(t.p,{children:"You will need:"}),"\n",(0,s.jsxs)(t.ul,{children:["\n",(0,s.jsx)(t.li,{children:"Docker command line tool"}),"\n",(0,s.jsx)(t.li,{children:"Python 3"}),"\n"]}),"\n",(0,s.jsx)(t.h2,{id:"getting-started",children:"Getting started"}),"\n",(0,s.jsx)(t.p,{children:"To download the image, issue the following commands in your terminal:"}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"docker pull fnndsc/docker-swift-onlyone\n"})}),"\n",(0,s.jsx)(t.h2,{id:"setup-volume-for-swift",children:"Setup Volume for Swift"}),"\n",(0,s.jsxs)(t.blockquote,{children:["\n",(0,s.jsxs)(t.p,{children:[(0,s.jsx)(t.a,{href:"https://docs.docker.com/storage/volumes/",children:"Volumes"})," are the preferred mechanism for persisting data generated by and\nused by Docker containers."]}),"\n"]}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"docker volume create swift_storage\n"})}),"\n",(0,s.jsx)(t.h2,{id:"start-up-container-instance",children:"Start up Container Instance"}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"docker run -d --name swift-onlyone -p 12345:8080 -v swift_storage:/srv -t fnndsc/docker-swift-onlyone\n"})}),"\n",(0,s.jsx)(t.h2,{id:"setup-swift-client",children:"Setup Swift Client"}),"\n",(0,s.jsxs)(t.p,{children:["Querying Swift through command line requires ",(0,s.jsx)(t.a,{href:"https://pypi.org/project/python-swiftclient/",children:"python-swiftclient"}),". We'll\nneed to install it:"]}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"pip3 pip install python-swiftclient\n"})}),"\n",(0,s.jsxs)(t.blockquote,{children:["\n",(0,s.jsx)(t.p,{children:"If you are on Mac OS, which has Apple Swift overriding Openstack swift package, explicitly invoking swiftclient as\n/usr/bin/local/swift works."}),"\n",(0,s.jsxs)(t.p,{children:["In case the absolute path is not ",(0,s.jsx)(t.code,{children:"/usr/bin/local/swift"}),", try ",(0,s.jsx)(t.code,{children:"sudo find / -name swift"})," which will give you a list\nthat contains the right > executable path"]}),"\n",(0,s.jsxs)(t.p,{children:["For example, ",(0,s.jsx)(t.code,{children:"/usr/local/bin/swift -A http://127.0.0.1:12345/auth/v1.0 -U chris:chris1234 -K testing stat"})," should\nwork. We will keep using ",(0,s.jsx)(t.code,{children:"/usr/local/bin/swift"})," afterwards"]}),"\n"]}),"\n",(0,s.jsx)(t.h2,{id:"create-upload-and-download-a-test-file",children:"Create, Upload, and Download a Test File"}),"\n",(0,s.jsxs)(t.p,{children:["We've create an empty file called ",(0,s.jsx)(t.code,{children:"test-file.txt"})," to get you started:"]}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"touch test-file.txt\n"})}),"\n",(0,s.jsx)(t.p,{children:"To upload this file onto Swift:"}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"/usr/local/bin/swift -A http://127.0.0.1:12345/auth/v1.0 -U chris:chris1234 -K testing upload --object-name\ntest-file.txt user_uploads ./test-file.txt\n"})}),"\n",(0,s.jsx)(t.p,{children:"which will print the name of the file if the upload was successful:"}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"test-file.txt\n"})}),"\n",(0,s.jsx)(t.p,{children:"To download that file:"}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"/usr/local/bin/swift -A http://127.0.0.1:12345/auth/v1.0 -U chris:chris1234 -K testing download user_uploads test-file.txt\n"})})]})}function h(e={}){const{wrapper:t}={...(0,i.R)(),...e.components};return t?(0,s.jsx)(t,{...e,children:(0,s.jsx)(d,{...e})}):d(e)}},8453:(e,t,n)=>{n.d(t,{R:()=>o,x:()=>a});var s=n(6540);const i={},l=s.createContext(i);function o(e){const t=s.useContext(l);return s.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function a(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:o(e.components),s.createElement(l.Provider,{value:t},e.children)}}}]);