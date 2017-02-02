#!/usr/bin/env groovy

def call(name) {
  // evaluate the body block, and collect configuration into the object
  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  //Function for converting Map -> Json
  @NonCPS
  def GenJson(Map input){
    def json = new groovy.json.JsonBuilder(input)
    return json.toString()
  }
      
  //Create Release Map
  def release_map = [
    'version': config.version,
    'tag_name': config.tag_name,
    'target_commitish': config.branch,
    'name': config.name,
    'body': config.body,
    'draft': config.draft,
    'prerelease': config.prerelease
  ]

  //Convert Release Map to Json
  def release_json = GenJson(release_map)

  //Create Release on Github
  def release_res = sh(returnStdout: true, script:"""curl -s -X POST -H "Authorization: token \${config.oauth_token}" ${config.release_url} -d '${config.release_json}'""").trim()
  return release_res
}
