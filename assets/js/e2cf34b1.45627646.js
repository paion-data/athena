"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[443],{9439:(e,i,a)=>{a.r(i),a.d(i,{assets:()=>l,contentTitle:()=>s,default:()=>h,frontMatter:()=>r,metadata:()=>o,toc:()=>d});var n=a(4848),t=a(8453);const r={title:"JSON API",sidebar_position:1},s=void 0,o={id:"client-apis/json-api",title:"JSON API",description:"[//]: # (Copyright 2024 Paion Data)",source:"@site/docs/client-apis/json-api.md",sourceDirName:"client-apis",slug:"/client-apis/json-api",permalink:"/athena/docs/client-apis/json-api",draft:!1,unlisted:!1,editUrl:"https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/docs/client-apis/json-api.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{title:"JSON API",sidebar_position:1},sidebar:"docSidebar",previous:{title:"Client APIs",permalink:"/athena/docs/client-apis"},next:{title:"File Stores",permalink:"/athena/docs/filestore"}},l={},d=[{value:"Hierarchical URLs",id:"hierarchical-urls",level:2},{value:"File Identifiers",id:"file-identifiers",level:2}];function c(e){const i={a:"a",admonition:"admonition",code:"code",h2:"h2",li:"li",ol:"ol",p:"p",pre:"pre",...(0,t.R)(),...e.components};return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsxs)(i.p,{children:[(0,n.jsx)(i.a,{href:"https://jsonapi.org",children:"JSON-API"})," is a specification for building REST APIs for CRUID (create, read, update, and delete)\noperations."]}),"\n",(0,n.jsx)(i.p,{children:"Similar to GraphQL, it allows the client to control what is returned in the response payload. Unlike GraphQL, the\nJSON-API spells out hot to perform file operations instead of file metadata operations."}),"\n",(0,n.jsxs)(i.p,{children:["JSON-API has no standardized schema introspection. However, Athena adds this capability to file service by exporting an\n",(0,n.jsx)(i.a,{href:"https://www.openapis.org",children:"Open API Initiative"})," document (formerly known as ",(0,n.jsx)(i.a,{href:"https://swagger.io",children:"Swagger"}),"). The\n",(0,n.jsx)(i.a,{href:"https://jsonapi.org/format/",children:"json-api specification"})," is the best reference for understanding JSON-API.\nThe following sections describe commonly used JSON-API features as well as Athena additions for filtering and swagger."]}),"\n",(0,n.jsx)(i.h2,{id:"hierarchical-urls",children:"Hierarchical URLs"}),"\n",(0,n.jsxs)(i.p,{children:["Athena generally follows the ",(0,n.jsx)(i.a,{href:"http://jsonapi.org/recommendations/",children:"JSON-API recommendations"})," for URL design."]}),"\n",(0,n.jsxs)(i.p,{children:["Athena currently requires all files to be addressed by ID within a URL parameter. For example, downloading a file with\nan ID of 1 must be fully qualified by ID: ",(0,n.jsx)(i.code,{children:"/file/download?fileId=1"})]}),"\n",(0,n.jsx)(i.h2,{id:"file-identifiers",children:"File Identifiers"}),"\n",(0,n.jsx)(i.p,{children:"Athena supports two mechanisms by which a newly uploaded file is assigned an ID:"}),"\n",(0,n.jsxs)(i.ol,{children:["\n",(0,n.jsxs)(i.li,{children:["The file is assigned by a ",(0,n.jsx)(i.a,{href:"https://github.com/paiondata/athena/blob/master/athena-core/src/main/java/io/github/paiondata/athena/file/identifier/FileNameAndUploadedTimeBasedIdGenerator.java",children:"fixed-length ID"})," and saved in the data store."]}),"\n",(0,n.jsxs)(i.li,{children:["The application provides an ID which is generated by a custom ",(0,n.jsx)(i.a,{href:"https://github.com/paiondata/athena/blob/master/athena-core/src/main/java/io/github/paiondata/athena/file/identifier/FileIdGenerator.java",children:"FileIdGenerator"})," implementation  by the application"]}),"\n"]}),"\n",(0,n.jsxs)(i.admonition,{type:"note",children:[(0,n.jsxs)(i.p,{children:["In order to replace the ",(0,n.jsx)(i.a,{href:"https://github.com/paiondata/athena/blob/master/athena-core/src/main/java/io/github/paiondata/athena/file/identifier/FileNameAndUploadedTimeBasedIdGenerator.java",children:"default file ID generator"}),", the custom implementation\nmust be bound explicitly through ",(0,n.jsx)(i.a,{href:"https://github.com/paiondata/athena/blob/master/athena-core/src/main/java/io/github/paiondata/athena/application/AbstractBinderFactory.java",children:"BinderFactory"}),". For example, when application implements a\ngenerator called 'MyIdGenerator', it will load via"]}),(0,n.jsx)(i.pre,{children:(0,n.jsx)(i.code,{className:"language-java",children:"@Override\nprotected Class<? extends FileIdGenerator> buildFileIdGenerator() {\n    return MyIdGenerator.class\n}\n"})}),(0,n.jsxs)(i.p,{children:["For more info on custom binding, please check out\n",(0,n.jsx)(i.a,{href:"../contributing/jersey-di-using-hk2",children:"Basic Dependency Injection using Jersey's HK2"})]})]})]})}function h(e={}){const{wrapper:i}={...(0,t.R)(),...e.components};return i?(0,n.jsx)(i,{...e,children:(0,n.jsx)(c,{...e})}):c(e)}},8453:(e,i,a)=>{a.d(i,{R:()=>s,x:()=>o});var n=a(6540);const t={},r=n.createContext(t);function s(e){const i=n.useContext(r);return n.useMemo((function(){return"function"==typeof e?e(i):{...i,...e}}),[i,e])}function o(e){let i;return i=e.disableParentContext?"function"==typeof e.components?e.components(t):e.components||t:s(e.components),n.createElement(r.Provider,{value:i},e.children)}}}]);