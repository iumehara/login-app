import React from 'react'
import { render } from 'react-dom'
import UsersTable from './UsersTable'

class UsersComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {users: []}
  }

  getUsers() {
    this.setState({
      users: [
        {id: '1', name: 'adam'},
        {id: '2', name: 'bob'},
        {id: '3', name: 'cam'},
        {id: '4', name: 'dan'}
      ]
    })
  }

  componentWillMount() {
    this.getUsers()
  }

  render() {
    return (
      <UsersTable users={this.state.users}/>
    )
  }
}

export default UsersComponent
