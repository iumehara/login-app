import React from 'react'
import { render } from 'react-dom'

class UsersTable extends React.Component {
  constructor(props) {
    super(props)
  }

  render() {
    let users = this.props.users.map((user) => {
      return (
        <tr key={user.id}>
          <td>{user.id}</td>
          <td>{user.name}</td>
        </tr>
      )
    })

    return (
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          {users}
        </tbody>
      </table>
    )
  }
}

UsersTable.propTypes = {
  users: React.PropTypes.arrayOf(
    React.PropTypes.shape({
      id: React.PropTypes.string,
      name: React.PropTypes.string
    }).isRequired
  ).isRequired
}

export default UsersTable
