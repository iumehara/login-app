import React from 'react'
import { render } from 'react-dom'

class UserDetail extends React.Component {
  render() {
    return (
      <div>
        <div className='username'>Username: {this.props.user.username}</div>
        <div className='role'>Role: {this.props.user.role}</div>
      </div>
    )
  }
}

UserDetail.propTypes = {
  user: React.PropTypes.shape({
    username: React.PropTypes.string.isRequired,
    role: React.PropTypes.string
  }).isRequired
}

export default UserDetail
