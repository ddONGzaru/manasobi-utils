package io.manasobi.utils

import groovy.io.FileType
import spock.lang.Shared
import spock.lang.Specification

class FileUtilsTest extends Specification {
	
	@Shared File file
	
	def setupSpec() {
		
		File.createTempFile('temp_', 'txt').with {
			write 'FileUtils의 byteCountToDisplaySize() 테스트'
			file = new File(absolutePath) 
		}
	}
	
	def "byteCountToDisplaySize() :: byte단위의 파일 사이즈를 읽기 쉽도록 EB, PB, TB, GB, MB, KB, bytes 단위로 파싱하여 반환"() {
		
		when:
			String fileSize = FileUtils.byteCountToDisplaySize(file)
		then:
			fileSize == '47 bytes'
			
		expect:
			FileUtils.byteCountToDisplaySize(size) == result
		where:
			size 	  || result
			1024768   || '1000 KB'
			3000 	  || '2 KB'
			456789000 || '435 MB'	
	}
	
	/*def "cleanDir() :: 루트 디렉토리 내의 파일 및 하위 디렉토리를 삭제 [루트 디렉토리는 삭제하지 않음]"() {
		
		setup:
		
			new File("$TestUtils.resultsetDir/utils/temp.dat").createNewFile()
		
			def cleanDirPath = "$TestUtils.resultsetDir/utils/"
			def cleanDir = new File(cleanDirPath)
			
			cleanDir.eachFileRecurse(FileType.FILES) {
				it.delete()
			}
			
			def fileNames = ['cleanDir_1', 'cleanDir_2', 'cleanDir_3']
			
			fileNames.each {
				def file = new File("$cleanDir/$it" + ".txt")
				file << 'cleanDir() 테스트를 위한 더미 파일'
			}
			
		when:
			Status cleanResult = FileUtils.cleanDir(cleanDir)
		then:
			cleanResult == Status.SUCCESS
			cleanDir.list().size() == 0
			
			new File("$TestUtils.resultsetDir/utils/temp.dat").createNewFile()
			
		expect:
			FileUtils.cleanDir(dir) == result			
		where:
			dir 							 		  || result
			new File('C:/test/cleanDir/notExistsDir') || Status.FAIL // 존재하지 않는 디렉토리
			new File('C:/test/cleanDir/notDir.txt')   || Status.FAIL // 존재하지 않는 파일
			'C:/test/cleanDir/notExistsDir' 		  || Status.FAIL // 존재하지 않는 디렉토리
			'C:/test/cleanDir/notDir.txt' 			  || Status.FAIL // 존재하지 않는 파일
	}*/
	
	/*def cleanupSpec() {
		
		def fileNames = ['crypto/readme', 'filegateway/readme', 'pdftools/readme', 'utils/readme']
		
		fileNames.each {
			def file = new File("$TestUtils.resultsetDir/$it")
			file << 'git에서 빈 폴더가 commit되지 않는 관계로 폴더 생성을 위한 더미 파일을 추가.'
		}
	}*/
	
	
}



/*
def f1= new File('File1.txt')
f1 << 'abcdefg'*/