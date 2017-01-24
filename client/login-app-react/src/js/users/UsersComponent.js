import React from 'react'
import { render } from 'react-dom'
import UsersTable from './UsersTable'
import { getUsers } from '../fetcher'
import auth from '../auth'

class UsersComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {users: []}
  }

  getUsersAndSetToState() {
    return getUsers(auth.getToken())
      .then((users) => {
        this.setState({users})
      })
  }

  componentWillMount() {
    this.getUsersAndSetToState()
  }

  render() {
    let usersTable = () => {
      if (this.state.users) {
        return <UsersTable users={this.state.users}/>
      }
    }

    return (
      <div>
        <h1>Users</h1>
        {usersTable()}
      </div>
    )
  }
}

export default UsersComponent
