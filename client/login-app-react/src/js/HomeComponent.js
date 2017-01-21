import React from 'react'
import { render } from 'react-dom'
import auth from './auth'
import UserDetail from './users/UserDetail'
class HomeComponent extends React.Component {
  render() {
    let currentUserInfo = () => {
      if (auth.isLoggedIn()) {
        return <UserDetail user={{username: auth.getUsername()}}/>
      } else {
        return <div>No User Data. Please log in.</div>
      }
    }

    return (
      <div>
        <h1>Home</h1>
        {currentUserInfo()}
      </div>
    )
  }
}

export default HomeComponent
