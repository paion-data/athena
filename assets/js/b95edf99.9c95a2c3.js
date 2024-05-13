"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[166],{3324:(e,s,r)=>{r.r(s),r.d(s,{assets:()=>o,contentTitle:()=>c,default:()=>h,frontMatter:()=>n,metadata:()=>l,toc:()=>d});var t=r(4848),i=r(8453);const n={title:"Key Performance Indicators - Athena Web Service",sidebar_position:7},c=void 0,l={id:"kpi",title:"Key Performance Indicators - Athena Web Service",description:"[//]: # (Copyright 2024 Paion Data)",source:"@site/docs/kpi.md",sourceDirName:".",slug:"/kpi",permalink:"/docs/kpi",draft:!1,unlisted:!1,editUrl:"https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/docs/kpi.md",tags:[],version:"current",sidebarPosition:7,frontMatter:{title:"Key Performance Indicators - Athena Web Service",sidebar_position:7},sidebar:"docSidebar",previous:{title:"Logging Guidelines",permalink:"/docs/logging-guidelines"},next:{title:"Contributor Guide",permalink:"/docs/category/contributor-guide"}},o={},d=[{value:"Server Error Responses (HTTP 5XX)",id:"server-error-responses-http-5xx",level:2},{value:"Swift Errors",id:"swift-errors",level:2},{value:"Requests",id:"requests",level:2},{value:"System Metrics",id:"system-metrics",level:2},{value:"Latency",id:"latency",level:2},{value:"Rate Limiting Rejections",id:"rate-limiting-rejections",level:2},{value:"Active Requests",id:"active-requests",level:2},{value:"Bad Request Responses (HTTP 4XX)",id:"bad-request-responses-http-4xx",level:2}];function a(e){const s={code:"code",em:"em",h2:"h2",li:"li",p:"p",ul:"ul",...(0,i.R)(),...e.components};return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(s.p,{children:"These are the key performance indicators for the Athena Web Service component, listed in categories by order of\nimportance."}),"\n",(0,t.jsx)(s.h2,{id:"server-error-responses-http-5xx",children:"Server Error Responses (HTTP 5XX)"}),"\n",(0,t.jsxs)(s.p,{children:["Shows how much trouble ",(0,t.jsx)(s.em,{children:"the service"})," is having."]}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.responseCodes.serverError.m1_rate"})}),"\n"]}),"\n",(0,t.jsx)(s.h2,{id:"swift-errors",children:"Swift Errors"}),"\n",(0,t.jsx)(s.p,{children:"Shows how much trouble queries are having against swift."}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"swift.errors.exceptions.m1_rate"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"swift.errors.http.m1_rate"})}),"\n"]}),"\n",(0,t.jsx)(s.h2,{id:"requests",children:"Requests"}),"\n",(0,t.jsx)(s.p,{children:"Shows how many requests the service is serving."}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.requests.m1_rate"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.requests.m15_rate"})}),"\n"]}),"\n",(0,t.jsx)(s.h2,{id:"system-metrics",children:"System Metrics"}),"\n",(0,t.jsx)(s.p,{children:"Shows the overall health of the system's low-level resources and activities."}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"CPU"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"Memory"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"Network IO"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"GC Pauses"})}),"\n"]}),"\n",(0,t.jsx)(s.h2,{id:"latency",children:"Latency"}),"\n",(0,t.jsx)(s.p,{children:"Shows duration of overall requests and druid requests. (m1_rate and pN)"}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.requests.p50"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.requests.p75"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.requests.p95"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.requests.p98"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.requests.p99"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.requests.p999"})}),"\n"]}),"\n",(0,t.jsx)(s.h2,{id:"rate-limiting-rejections",children:"Rate Limiting Rejections"}),"\n",(0,t.jsx)(s.p,{children:"Shows if users are hitting rate limits."}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"ratelimit.meter.reject.ui.m1_rate"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"ratelimit.meter.reject.user.m1_rate"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"ratelimit.meter.reject.global.m1_rate"})}),"\n"]}),"\n",(0,t.jsx)(s.h2,{id:"active-requests",children:"Active Requests"}),"\n",(0,t.jsx)(s.p,{children:"Shows load at a given point in time. (ie. how close are the load is to the limits of Swift)"}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.activeRequests.count"})}),"\n"]}),"\n",(0,t.jsx)(s.h2,{id:"bad-request-responses-http-4xx",children:"Bad Request Responses (HTTP 4XX)"}),"\n",(0,t.jsx)(s.p,{children:"Shows how much trouble users are having interacting with the API."}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.responseCodes.badRequest.m1_rate"})}),"\n",(0,t.jsx)(s.li,{children:(0,t.jsx)(s.code,{children:"com.codahale.metrics.servlet.AbstractInstrumentedFilter.responseCodes.notFound.m1_rate"})}),"\n"]})]})}function h(e={}){const{wrapper:s}={...(0,i.R)(),...e.components};return s?(0,t.jsx)(s,{...e,children:(0,t.jsx)(a,{...e})}):a(e)}},8453:(e,s,r)=>{r.d(s,{R:()=>c,x:()=>l});var t=r(6540);const i={},n=t.createContext(i);function c(e){const s=t.useContext(n);return t.useMemo((function(){return"function"==typeof e?e(s):{...s,...e}}),[s,e])}function l(e){let s;return s=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:c(e.components),t.createElement(n.Provider,{value:s},e.children)}}}]);