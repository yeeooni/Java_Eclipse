package com.kitri.haksa.service.db;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.haksa.data.HaksaDto;

public class HaksaDBServiceImple implements HaksaService {

	private BufferedReader in;
	List<HaksaDao> list = new ArrayList<HaksaDao>();
	HaksaDao haksadao;
	HaksaDto haksadto;
	
	public HaksaDBServiceImple() {
		super();
		in = new BufferedReader(new InputStreamReader(System.in));
		haksadao = new HaksaDao();
		haksadto = new HaksaDto();
	}

	@Override
	public void menu() {
		// 메뉴 화면 입니다.

		System.out.println("===== 메뉴 선택 =====");
		System.out.print("1. 등록" + "\n" + "2. 찾기" + "\n" + "3. 삭제" + "\n" + "4. 전체 출력" );
		System.out.println("------------------");
		System.out.println("0. 종료");
		System.out.println("------------------");

		try {
			System.out.print("번호를 선택해주세요 : ");

			String str = in.readLine();

			switch (str) {

			case "1":
				registerMenu();
				break;
			case "2":
				findNameMenu();
				break;
			case "3":
				deleteMenu();
				break;
			case "4":
				selectAll();
				break;
			case "0":
				processExit();
				break;
			default:
				System.out.println("번호를 다시 선택해주세요.");
				menu();
				break;
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// 넘버포맷 잡는 메소드
	private static boolean isNumber(String age) {
		boolean flag = true;
		int len = age.length();
		for (int i = 0; i < len; i++) {
			int num = age.charAt(i) - 48;
			if (num < 0 || num > 9) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Override
	public void registerMenu() {

		try {

			String number = null;
			String value = null;
			String age1 = "";
			int age = 0;
			System.out.println("===== 메뉴선택 =====");
			System.out.println(" 1.학생\n 2.교수\n 3.관리자\n 4.이전메뉴");
			System.out.print("번호를 선택해주세요 : ");
//			in = new BufferedReader(new InputStreamReader(System.in));
			number = in.readLine().trim();

			switch (number) {

			case "1":
				while (true) {
					System.out.print("나이 : ");
//				int age = Integer.parseInt(in.readLine().trim());

					age1 = in.readLine().trim();
					if (isNumber(age1)) {
						age = Integer.parseInt(age1);
						break;
					} else {
						System.out.println("숫자가 아닙니다. 나이는 숫자만 가능합니다.");

					}
				}
				System.out.print("이름 : ");
				String name = in.readLine().trim();

				System.out.println("학번 : ");
				value = in.readLine().trim();
				resister(new HaksaDto(age, name, Integer.parseInt(number) - 1, value));
				menu();
				break;

			case "2":
				System.out.print("나이 : ");
				age = Integer.parseInt(in.readLine().trim());
				System.out.print("이름 : ");
				name = in.readLine().trim();

				System.out.println("과목 : ");
				value = in.readLine().trim();
				resister(new HaksaDto(age, name, Integer.parseInt(number) - 1, value));
				menu();
				break;

			case "3":
				System.out.println("나이 : ");
				age = Integer.parseInt(in.readLine().trim());
				System.out.println("이름 : ");
				name = in.readLine().trim();

				System.out.println("부서 : ");
				value = in.readLine().trim();
				resister(new HaksaDto(age, name, Integer.parseInt(number) - 1, value));
				menu();
				break;

			case "4":
				menu();
				break;

			default:
				System.out.print("번호를 다시 선택 해주세요.");
				registerMenu();
				break;
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void findNameMenu() {

		try {
			System.out.println("찾을 이름을 입력해 주세요 : ");
//			in = new BufferedReader(new InputStreamReader(System.in));

			String name = in.readLine().trim();
			HaksaDto hd = findName(name);

			if (hd != null) {
				System.out.println("나이 : " + hd.getAge() + "\t이름 : " + hd.getName() + "\t" + hd.getKey() + " : "
						+ hd.getValue());
			} else
				System.out.println("찾을 수 없습니다.");

			System.out.println("계속 하시려면 1, 종료 하시려면 0을 입력해주세요.");
			String input = in.readLine();
			int ip = Integer.parseInt(input);

			if (ip == 1) {
				menu();
			} else if (ip == 0)
				processExit();
			else if (ip > 1) {
				System.out.println("숫자만 입력해주세요");

				System.out.println("계속 하시려면 1, 종료 하시려면 0을 입력해주세요.");
				input = in.readLine();
				ip = Integer.parseInt(input);

				if (ip == 1) {
					menu();
				} else if (ip == 0)
					processExit();

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void resister(HaksaDto haksa) {

	}
	
	
	@Override
	public HaksaDto findName(String name) {

		int size = list.size();

		for (int i = 0; i < size; i++) {
			if (name.equals(list.get(i).getName())) {
				return list.get(i);
			}
		}
		return null;
	}

	@Override
	public void deleteMenu() {
		System.out.println("삭제할 사람의 이름을 입력해주세요.");

		try {
			String name = in.readLine();

//			int deleteName = delete(in.readLine());

			if (delete(name) == 1) {
				System.out.println(name + "님을 삭제하였습니다.");
			} else if (delete(name) == 0) {
				System.out.println("삭제할 사람이 없습니다.");
			}

			System.out.println("계속 하시려면 1, 종료 하시려면 0을 입력해주세요.");
			String input = in.readLine();
			int ip = Integer.parseInt(input);

			if (ip == 1) {
				menu();
			} else if (ip == 0)
				processExit();
			else {
				System.out.println("숫자만 입력해주세요");

				System.out.println("계속 하시려면 1, 종료 하시려면 0을 입력해주세요.");
				input = in.readLine();
				ip = Integer.parseInt(input);

				if (ip == 1) {
					menu();
				} else if (ip == 0)
					processExit();

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public int delete(String name) {

		int size = list.size();

		for (int i = 0; i < size; i++) {
			if (name.equals(list.get(i).getName())) {
				list.remove(i);
				return 1;
			}
		}

		return 0;
	}

	@Override
	public void selectAll() {
		
		
		int size = list.size();

		for (int i = 0; i < size; i++) {
			System.out.println("나이 : " + list.get(i).getAge() + "\t이름 : " + list.get(i).getName() + "\t"
					+ list.get(i).getKey() + .getkeyName() +  " : " + list.get(i).getValue());
		}

		System.out.println("계속 하시려면 1, 종료 하시려면 0을 입력해주세요.");
		String input;
		try {
			input = in.readLine();
			int ip = Integer.parseInt(input);

			if (ip == 1) {
				menu();
			} else if (ip == 0)
				processExit();
			else {
				System.out.println("숫자만 입력해주세요");

				System.out.println("계속 하시려면 1, 종료 하시려면 0을 입력해주세요.");
				input = in.readLine();
				ip = Integer.parseInt(input);

				if (ip == 1) {
					menu();
				} else if (ip == 0)
					processExit();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void processExit() {
		System.out.println("프로그램이 종료되었습니다.");
		System.exit(0);

	}

}
