import React from 'react'
import { render } from 'react-dom'
import { Link } from 'react-router'

class UsersTable extends React.Component {
  constructor(props) {
    super(props)

    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleSubmit(e) {
    e.preventDefault()

    const userParams = {
      username: this.refs.username.value,
      password: 'secret',
      role: this.refs.role.value
    }

    this.props.createUser(userParams)
  }

  render() {
    let users = this.props.users.map((user) => {
      return (
        <tr className={user.username} key={user.id}>
          <td>{user.id}</td>
          <td>
            <Link className={user.username} to={`users/${user.username}`}>
              {user.username}
            </Link>
          </td>
          <td>{user.role}</td>
          <td></td>
        </tr>
      )
    })

    return (
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Role</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {users}
          <tr>
            <td></td>
            <td><input ref='username' placeholder='username'></input></td>
            <td><input ref='role' placeholder='password'></input></td>
            <td onClick={this.handleSubmit}>+</td>
          </tr>
        </tbody>
      </table>
    )
  }
}

UsersTable.propTypes = {
  users: React.PropTypes.arrayOf(
    React.PropTypes.shape({
      id: React.PropTypes.number,
      name: React.PropTypes.string,
      role: React.PropTypes.string
    }).isRequired
  ).isRequired
}

export default UsersTable
