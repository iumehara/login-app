import React from 'react'
import { render } from 'react-dom'
import { hashHistory, Route, Router } from 'react-router'
import About from './About'
import App from './App'
import Login from './Login'
import Logout from './Logout'
import UsersComponent from './UsersComponent'
import UserDetailComponent from './UserDetailComponent'

render(
  <Router history={hashHistory}>
    <Route path="/" component={App}>
      <Route path="login" component={Login} />
      <Route path="logout" component={Logout} />
      <Route path="about" component={About} />
      <Route path="users" component={UsersComponent} />
      <Route path="users/:username" component={UserDetailComponent} />
    </Route>
  </Router>,
  document.getElementById('login-example')
)
