
println()
println(WORKSPACE)

evaluate(new File("job1.groovy"))
evaluate(new File("job2.groovy"))

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