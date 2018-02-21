
println()
println(WORKSPACE)

def vipreNisDir = WORKSPACE + File.separator + "vipre-nis"

println(vipreNisDir)

evaluate(new File(vipreNisDir + File.separator + "job1.groovy"))
evaluate(new File(vipreNisDir + File.separator + "job2.groovy"))

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