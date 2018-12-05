#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>

#define _GNU_SOURCE

void showIP();

int main()
{
//	showIP();
	pid_t pid = fork();
	printf("pid=%d\n",pid);
	if(pid==0){
		int flag = setuid(800);
		printf("3\n");
		showIP();
		execl("ls", "ls","-al", "/etc/passwd", (char *)0);
	}else{
		showIP();
		printf("1\n");
		wait(NULL);
		printf("2\n");
		showIP();
	}
}

void showIP()
{
	uid_t ruid,euid,suid;
	getresuid(&ruid,&euid,&suid);
	printf("ruid=%d,euid=%d,suid=%d\n",ruid,euid,suid);
}
