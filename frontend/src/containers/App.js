import React from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'

import { Dimmer } from 'semantic-ui-react'

import ToolBar from './ToolBar'
import Desktop from './Desktop'
import Footer from './Footer'

import '../assets/components.css'

const App = ({isAppMenuActive}) => (
  <Dimmer.Dimmable blurring dimmed={isAppMenuActive}>
    <div className='components'>
      <ToolBar/>
      <Desktop/>
      <Footer/>
    </div>
  </Dimmer.Dimmable>
)

App.propTypes = {
  isAppMenuActive: PropTypes.bool.isRequired
}

const mapStateToProps = state => ({
  isAppMenuActive: state.toolbar.isAppMenuActive
})

export default connect(
  mapStateToProps
)(App)
