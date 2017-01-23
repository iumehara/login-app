import React from 'react'
import { render } from 'react-dom'
import { hashHistory, Router, Route, IndexRoute } from 'react-router'
import '../scss/index.scss'
import App from './App'
import Login from './Login'
import HomeComponent from './HomeComponent'
import UsersComponent from './users/UsersComponent'
import UserDetailComponent from './users/UserDetailComponent'
import NewUserComponent from './users/NewUserComponent'

render(
  <Router history={hashHistory}>
    <Route path="/" component={App}>
      <IndexRoute component={HomeComponent} />
      <Route path="login" component={Login} />
      <Route path="users" component={UsersComponent} />
      <Route path="users/new" component={NewUserComponent} />
      <Route path="users/:username" component={UserDetailComponent} />
    </Route>
  </Router>,
  document.getElementById('login-example')
)
