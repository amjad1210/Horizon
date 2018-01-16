import React from 'react'
import PropTypes from 'prop-types'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'

import WindowManager from '../components/Window/WindowManager'

import NetworkWindow from '../components/Windows/NetworkWindow'

import * as WindowActions from '../actions/WindowActions'

const Desktop = ({openedWindows, actions}) => (
  <div className='desktop'>
    <NetworkWindow/>
  </div>
)

Desktop.propTypes = {
  openedWindows: PropTypes.object.isRequired,
  actions: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  openedWindows: state.windows.openedWindows
})

const mapDispatchToProps = dispatch => ({
  actions: bindActionCreators(WindowActions, dispatch)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Desktop)
