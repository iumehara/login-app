import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import UserDetail from '../../src/js/users/UserDetail'

describe('UserDetail', () => {
  it('renders  user name', () => {
    const user = {id: 1, username: 'adam', role: 'staff'}

    const userDetail = shallow(<UserDetail user={user}/>)

    expect(userDetail.find('.username').text()).toInclude('Username: adam')
    expect(userDetail.find('.role').text()).toInclude('Role: staff')
  })
})
