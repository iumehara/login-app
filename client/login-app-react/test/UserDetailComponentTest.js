import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import UserDetailComponent from '../src/js/UserDetailComponent'

describe('UserDetail', () => {
  it('renders  user name', () => {
    let stubGetUserData = () => {
      return {
        then: (setStateImplementation) => {
          setStateImplementation(
            {id: 1, username: 'adam', role: 'staff'}
          )
        }
      }
    }

    let spy = expect.spyOn(
      UserDetailComponent.prototype, 'getUser'
    ).andReturn(
      stubGetUserData()
    )

    let userDetailComponent = shallow(<UserDetailComponent params={{username: 'adam'}}/>)

    expect(userDetailComponent.find('.username').text()).toInclude('Username: adam')
    expect(userDetailComponent.find('.role').text()).toInclude('Role: staff')
  })
})
