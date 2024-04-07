import React from 'react';
import ComponentCreator from '@docusaurus/ComponentCreator';

export default [
  {
    path: '/athena/__docusaurus/debug',
    component: ComponentCreator('/athena/__docusaurus/debug', '776'),
    exact: true
  },
  {
    path: '/athena/__docusaurus/debug/config',
    component: ComponentCreator('/athena/__docusaurus/debug/config', 'a71'),
    exact: true
  },
  {
    path: '/athena/__docusaurus/debug/content',
    component: ComponentCreator('/athena/__docusaurus/debug/content', '7d8'),
    exact: true
  },
  {
    path: '/athena/__docusaurus/debug/globalData',
    component: ComponentCreator('/athena/__docusaurus/debug/globalData', 'edc'),
    exact: true
  },
  {
    path: '/athena/__docusaurus/debug/metadata',
    component: ComponentCreator('/athena/__docusaurus/debug/metadata', 'b4e'),
    exact: true
  },
  {
    path: '/athena/__docusaurus/debug/registry',
    component: ComponentCreator('/athena/__docusaurus/debug/registry', '736'),
    exact: true
  },
  {
    path: '/athena/__docusaurus/debug/routes',
    component: ComponentCreator('/athena/__docusaurus/debug/routes', 'd7b'),
    exact: true
  },
  {
    path: '/athena/docs',
    component: ComponentCreator('/athena/docs', 'b7f'),
    routes: [
      {
        path: '/athena/docs',
        component: ComponentCreator('/athena/docs', 'f99'),
        routes: [
          {
            path: '/athena/docs',
            component: ComponentCreator('/athena/docs', 'b13'),
            routes: [
              {
                path: '/athena/docs/clientapis/',
                component: ComponentCreator('/athena/docs/clientapis/', '8d3'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/clientapis/jsonapi',
                component: ComponentCreator('/athena/docs/clientapis/jsonapi', '3b0'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/development/',
                component: ComponentCreator('/athena/docs/development/', 'ca7'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/development/jersey-di-using-hk2',
                component: ComponentCreator('/athena/docs/development/jersey-di-using-hk2', '85b'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/development/logging-guidelines',
                component: ComponentCreator('/athena/docs/development/logging-guidelines', '917'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/faq',
                component: ComponentCreator('/athena/docs/faq', 'd22'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/filestores/',
                component: ComponentCreator('/athena/docs/filestores/', '390'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/filestores/local-swift',
                component: ComponentCreator('/athena/docs/filestores/local-swift', 'd44'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/glossary',
                component: ComponentCreator('/athena/docs/glossary', '349'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/monitoring-and-operations/',
                component: ComponentCreator('/athena/docs/monitoring-and-operations/', 'd19'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/monitoring-and-operations/kpi',
                component: ComponentCreator('/athena/docs/monitoring-and-operations/kpi', 'd82'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/start',
                component: ComponentCreator('/athena/docs/start', 'cfc'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/system-config',
                component: ComponentCreator('/athena/docs/system-config', 'e33'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/test',
                component: ComponentCreator('/athena/docs/test', 'a3b'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/troubleshooting',
                component: ComponentCreator('/athena/docs/troubleshooting', '006'),
                exact: true,
                sidebar: "tutorialSidebar"
              },
              {
                path: '/athena/docs/welcome',
                component: ComponentCreator('/athena/docs/welcome', '85b'),
                exact: true,
                sidebar: "tutorialSidebar"
              }
            ]
          }
        ]
      }
    ]
  },
  {
    path: '*',
    component: ComponentCreator('*'),
  },
];
