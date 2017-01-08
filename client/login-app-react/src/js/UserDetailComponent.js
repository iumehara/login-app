import React from 'react'
import { render } from 'react-dom'
import { get } from './fetcher'

class UserDetailComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {user: null}
  }

  getUser(username) {
    return get('user', username)
  }

  getUserAndSetToState(username) {
    return this.getUser(username).then((user) => {
        this.setState({user})
      })
  }

  componentWillMount() {
    this.getUserAndSetToState(this.props.params.username)
  }

  render() {
    let userDetail = () => {
      if (this.state.user) {
        return (
          <div>
            <div className='username'>Username: {this.state.user.username}</div>
            <div className='role'>Role: {this.state.user.role}</div>
          </div>
        )
      }
    }

    return (
      <div>
        <h1>User</h1>
        {userDetail()}
      </div>
    )
  }
}

UserDetailComponent.propTypes = {
  params: React.PropTypes.shape({
    username: React.PropTypes.string.isRequired
  }).isRequired
}

export default UserDetailComponent
