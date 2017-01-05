import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import About from '../src/js/About'

describe('About page', () => {
  it('displays the component', () => {
    const about = shallow(<About/>)

    expect(about.contains(<h1>About</h1>)).toBe(true)
  })
})
