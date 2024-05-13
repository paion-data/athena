"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[416],{8880:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>c,contentTitle:()=>s,default:()=>h,frontMatter:()=>r,metadata:()=>a,toc:()=>d});var i=n(4848),o=n(8453);const r={title:"System Configuration",sidebar_position:4},s=void 0,a={id:"system-config",title:"System Configuration",description:"[//]: # (Copyright 2024 Paion Data)",source:"@site/docs/system-config.md",sourceDirName:".",slug:"/system-config",permalink:"/docs/system-config",draft:!1,unlisted:!1,editUrl:"https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/docs/system-config.md",tags:[],version:"current",sidebarPosition:4,frontMatter:{title:"System Configuration",sidebar_position:4},sidebar:"docSidebar",previous:{title:"Getting Started",permalink:"/docs/intro"},next:{title:"Monitoring and Operations",permalink:"/docs/monitoring-and-operations"}},c={},d=[{value:"Configuration Sources and Overrides",id:"configuration-sources-and-overrides",level:2}];function l(e){const t={a:"a",admonition:"admonition",code:"code",h2:"h2",p:"p",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",...(0,o.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(t.p,{children:"Athena has two main configuration avenues, the domain object configuration (File Store, Meta Store, and Data Fetchers)\nwhich happens via compiled Java code, and system configuration via properties. The domain configuration is\ncovered elsewhere, and we'll only cover the system configuration infrastructure here."}),"\n",(0,i.jsxs)(t.p,{children:["The system for property configuration that Athena uses lives in it's own ",(0,i.jsx)(t.a,{href:"https://github.com/paiondata/athena/tree/master/athena-system-config",children:"sub-module"}),". This system\nis extensible and reusable so that other Athena modules, and even other projects, can leverage it for their own property\nconfig needs. That sub-module has it's own deep set of documentation, so we'll be focusing only on how to use it for\nconfiguring Athena."]}),"\n",(0,i.jsx)(t.h2,{id:"configuration-sources-and-overrides",children:"Configuration Sources and Overrides"}),"\n",(0,i.jsxs)(t.p,{children:["Configuration for Athena modules come from only one location (that is, within the ",(0,i.jsx)(t.a,{href:"https://github.com/paiondata/athena/tree/master/athena-system-config",children:"sub-module"}),"\nitself) and allows for overriding other settings. This is particularly useful when overriding a property set in a module\nto turn off a feature, or to override a default configuration for your application in a certain environment, for\nexample."]}),"\n",(0,i.jsx)(t.p,{children:"Configuration sources are shown below, and are resolved in priority order, with higher-priority sources overriding\nsettings from lower-priority sources. Sources that are files will available to Athena on the Classpath for them to be\nloaded."}),"\n",(0,i.jsxs)(t.table,{children:[(0,i.jsx)(t.thead,{children:(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.th,{style:{textAlign:"center"},children:"Priority"}),(0,i.jsx)(t.th,{style:{textAlign:"center"},children:"Source"}),(0,i.jsx)(t.th,{style:{textAlign:"center"},children:"Notes"})]})}),(0,i.jsxs)(t.tbody,{children:[(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.td,{style:{textAlign:"center"},children:"(High) 1"}),(0,i.jsx)(t.td,{style:{textAlign:"center"},children:"Environment variables"}),(0,i.jsx)(t.td,{style:{textAlign:"center"}})]}),(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.td,{style:{textAlign:"center"},children:"2"}),(0,i.jsx)(t.td,{style:{textAlign:"center"},children:"Java properties"}),(0,i.jsx)(t.td,{style:{textAlign:"center"}})]}),(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.td,{style:{textAlign:"center"},children:"3"}),(0,i.jsx)(t.td,{style:{textAlign:"center"},children:(0,i.jsx)(t.code,{children:"userConfig.properties"})}),(0,i.jsx)(t.td,{style:{textAlign:"center"}})]}),(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.td,{style:{textAlign:"center"},children:"5"}),(0,i.jsx)(t.td,{style:{textAlign:"center"},children:(0,i.jsx)(t.code,{children:"applicationConfig.properties"})}),(0,i.jsx)(t.td,{style:{textAlign:"center"},children:"Every application MUST provide one of these"})]})]})]}),"\n",(0,i.jsx)(t.admonition,{type:"tip",children:(0,i.jsxs)(t.p,{children:["Since ",(0,i.jsx)(t.code,{children:"userConfig.properties"})," is often used while developing to turn features on and off, ",(0,i.jsx)(t.code,{children:".gitignore"})," includes\na  rule to ignore this file by default to help prevent checking it in accidentally."]})})]})}function h(e={}){const{wrapper:t}={...(0,o.R)(),...e.components};return t?(0,i.jsx)(t,{...e,children:(0,i.jsx)(l,{...e})}):l(e)}},8453:(e,t,n)=>{n.d(t,{R:()=>s,x:()=>a});var i=n(6540);const o={},r=i.createContext(o);function s(e){const t=i.useContext(r);return i.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function a(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(o):e.components||o:s(e.components),i.createElement(r.Provider,{value:t},e.children)}}}]);