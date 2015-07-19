package com.mycompany.dwsample;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DWPhotoApp extends Application<DWPhotoConfig>
{
    public static void main( String[] args ) throws Exception
    {
    	 new DWPhotoApp().run(args);
    }
    

    @Override
    public void initialize(Bootstrap<DWPhotoConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DWPhotoConfig configuration,
                    Environment environment) {
    	 final PhotoResource resource = new PhotoResource(
    		        configuration.getName()
    		    );
    		    environment.jersey().register(resource);
    }
}
