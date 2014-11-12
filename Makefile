job/target/sample-job-0.1.0-standalone.jar:
	cd job; lein uberjar
	
launch-local: job/target/sample-job-0.1.0-standalone.jar
	LEMUR_AWS_ACCESS_KEY=foo LEMUR_AWS_SECRET_KEY=bar lein run -m lemur.tool local lemur-scripts/minimal.clj --jar-src-path job.jar --keypair foo --main-class job.core