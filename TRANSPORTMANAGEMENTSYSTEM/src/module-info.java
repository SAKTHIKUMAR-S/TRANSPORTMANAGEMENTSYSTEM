/**
 * 
 */
/**
 * 
 */
module TRANSPORTMANAGEMENTSYSTEM {

	     requires java.base;
	    requires transitive java.sql;  // This makes java.sql accessible
	    requires java.logging;
		requires org.junit.jupiter.api;
	    
	    exports dao;
	    exports entity;
	    exports exception;
	    exports util;
	    exports main;
	    // Exporting packages for access by other modules
	     // For custom exceptions used throughout the application
	    
	  
	}

