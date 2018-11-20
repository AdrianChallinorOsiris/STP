package uk.co.osiris.stp.test;


import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.osiris.stp.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class CB extends Callback {
	
	public CB() {
		super();
	}
	
	public void exit(Token arg1) { }
	public void listLimit(Token arg1) {	}
	
	public void dbLoad(Token arg1) { }
	public void dbSave(Token arg1) { }
	public void dbReset(Token arg1) { }
	public void dbSize(Token arg1) {  }
	public void dbLock(Token arg1) {  }
	public void dbUnlock(Token arg1) {  }

	public void setDryRunYes(Token arg1) {   }
	public void setDryRunNo(Token arg1) {  }
	public void setDelayYes(Token arg1) {   }
	public void setDelayNo(Token arg1) {   }
	public void setWaitYes(Token arg1) {   }
	public void setWaitNo(Token arg1) {	 }
	public void setDetailYes(Token arg1) {  }
	public void setDetailNo(Token arg1) { }
	public void setHiddenYes(Token arg1) { 	}
	public void setHiddenNo(Token arg1) { 	}
	public void setLinksYes(Token arg1) {;	}
	public void setLinksNo(Token arg1) { }
	public void includeReset(Token arg1) { 	}
	public void excludeReset(Token arg1) { 	}
	public void include(Token arg1) { }
	public void exclude(Token arg1) { }
	public void showConfig(Token arg1) {}
	
	public void backup(Token arg) { 	}

	public void automatic(Token arg) {}
	
	public void s3Bucket(Token arg1) {		}

	public void s3Region(Token arg1) {}
	
	public void init(Token arg1) {  }

	public void listSelected(Token arg1) {  }
	public void listAuto(Token arg1) {  }
	
	public void listHosts(Token arg1) {  }
	public void listFolders(Token arg1) {  }
	public void listBackups(Token arg1) { }
	public void listFound(Token arg1) {  }
	public void listFiles(Token arg1) {  }
	public void listTree(Token arg1) {  }
	public void listVersions(Token arg1) {  }
	public void selectReset(Token arg1) {  }
	public void selectHostNumber(Token arg1) {  }
	public void selectFolderNumber(Token arg1) {  }
	public void selectHostName(Token arg1) { }
	public void selectFolderName(Token arg1) { }
	public void selectBackupNumber(Token arg1) {  }
	public void selectBackupDate(Token arg1) {  }
	public void fileReset(Token arg1) {  }
	public void fileAll(Token arg1) {  } 
	public void fileContains(Token arg1) {}
	public void fileRegex(Token arg1) {}
	public void selectFileNumber(Token arg1) { }
	
	
	public void addFolder(Token arg1)		{ 		}
	
	public void addTree(Token arg1)		{ 		}
	
	public void restoreToDirectory(Token arg1) 	{ 	}
	public void restoreToOriginal(Token arg1) 	{ 	}
	public void restoreGo(Token arg1) 			{ 				}
	public void restoreTimeStamp(Token arg1)	{ 		}
	public void restoreBareMetal(Token arg1)	{ 	}
	
	public void setSize(Token arg1) {  }
	
	public void setSizeMultiplier(Token arg1) {  }
	
	public void grammarCheck(Token arg1) {  }
	public void grammarPrint(Token arg1) {  }
	public void grammarPrune(Token arg1) {  }
	public void help(Token arg1) {  }
	public void memstats(Token arg1) {  }
}
