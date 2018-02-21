listView('githubView') {
    jobs {
      name('viprenis-rule-packager')
      name('viprenis-rules-packager-fortest')
    }
  
   columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}