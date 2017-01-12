import React from 'react'
import { render } from 'react-dom'
import { get } from './fetcher'
import auth from './auth'

class UserDetailComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      user: null,
      username: props.params.username
    }
  }

  getUser(username) {
    return get(auth.getToken(), 'user', username)
  }

  getUserAndSetToState(username) {
    return this.getUser(username).then((user) => {
        this.setState({user})
      })
  }

  componentWillMount() {
    this.getUserAndSetToState(this.state.username)
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      username: nextProps.params.username
    })
  }

  componentDidUpdate(prevProps, prevState) {
    if (this.state.username != prevState.username) {
      this.getUserAndSetToState(this.state.username)
    }
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
