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
		// �޴� ȭ�� �Դϴ�.

		System.out.println("===== �޴� ���� =====");
		System.out.print("1. ���" + "\n" + "2. ã��" + "\n" + "3. ����" + "\n" + "4. ��ü ���" );
		System.out.println("------------------");
		System.out.println("0. ����");
		System.out.println("------------------");

		try {
			System.out.print("��ȣ�� �������ּ��� : ");

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
				System.out.println("��ȣ�� �ٽ� �������ּ���.");
				menu();
				break;
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// �ѹ����� ��� �޼ҵ�
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
			System.out.println("===== �޴����� =====");
			System.out.println(" 1.�л�\n 2.����\n 3.������\n 4.�����޴�");
			System.out.print("��ȣ�� �������ּ��� : ");
//			in = new BufferedReader(new InputStreamReader(System.in));
			number = in.readLine().trim();

			switch (number) {

			case "1":
				while (true) {
					System.out.print("���� : ");
//				int age = Integer.parseInt(in.readLine().trim());

					age1 = in.readLine().trim();
					if (isNumber(age1)) {
						age = Integer.parseInt(age1);
						break;
					} else {
						System.out.println("���ڰ� �ƴմϴ�. ���̴� ���ڸ� �����մϴ�.");

					}
				}
				System.out.print("�̸� : ");
				String name = in.readLine().trim();

				System.out.println("�й� : ");
				value = in.readLine().trim();
				resister(new HaksaDto(age, name, Integer.parseInt(number) - 1, value));
				menu();
				break;

			case "2":
				System.out.print("���� : ");
				age = Integer.parseInt(in.readLine().trim());
				System.out.print("�̸� : ");
				name = in.readLine().trim();

				System.out.println("���� : ");
				value = in.readLine().trim();
				resister(new HaksaDto(age, name, Integer.parseInt(number) - 1, value));
				menu();
				break;

			case "3":
				System.out.println("���� : ");
				age = Integer.parseInt(in.readLine().trim());
				System.out.println("�̸� : ");
				name = in.readLine().trim();

				System.out.println("�μ� : ");
				value = in.readLine().trim();
				resister(new HaksaDto(age, name, Integer.parseInt(number) - 1, value));
				menu();
				break;

			case "4":
				menu();
				break;

			default:
				System.out.print("��ȣ�� �ٽ� ���� ���ּ���.");
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
			System.out.println("ã�� �̸��� �Է��� �ּ��� : ");
//			in = new BufferedReader(new InputStreamReader(System.in));

			String name = in.readLine().trim();
			HaksaDto hd = findName(name);

			if (hd != null) {
				System.out.println("���� : " + hd.getAge() + "\t�̸� : " + hd.getName() + "\t" + hd.getKey() + " : "
						+ hd.getValue());
			} else
				System.out.println("ã�� �� �����ϴ�.");

			System.out.println("��� �Ͻ÷��� 1, ���� �Ͻ÷��� 0�� �Է����ּ���.");
			String input = in.readLine();
			int ip = Integer.parseInt(input);

			if (ip == 1) {
				menu();
			} else if (ip == 0)
				processExit();
			else if (ip > 1) {
				System.out.println("���ڸ� �Է����ּ���");

				System.out.println("��� �Ͻ÷��� 1, ���� �Ͻ÷��� 0�� �Է����ּ���.");
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
		System.out.println("������ ����� �̸��� �Է����ּ���.");

		try {
			String name = in.readLine();

//			int deleteName = delete(in.readLine());

			if (delete(name) == 1) {
				System.out.println(name + "���� �����Ͽ����ϴ�.");
			} else if (delete(name) == 0) {
				System.out.println("������ ����� �����ϴ�.");
			}

			System.out.println("��� �Ͻ÷��� 1, ���� �Ͻ÷��� 0�� �Է����ּ���.");
			String input = in.readLine();
			int ip = Integer.parseInt(input);

			if (ip == 1) {
				menu();
			} else if (ip == 0)
				processExit();
			else {
				System.out.println("���ڸ� �Է����ּ���");

				System.out.println("��� �Ͻ÷��� 1, ���� �Ͻ÷��� 0�� �Է����ּ���.");
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
			System.out.println("���� : " + list.get(i).getAge() + "\t�̸� : " + list.get(i).getName() + "\t"
					+ list.get(i).getKey() + .getkeyName() +  " : " + list.get(i).getValue());
		}

		System.out.println("��� �Ͻ÷��� 1, ���� �Ͻ÷��� 0�� �Է����ּ���.");
		String input;
		try {
			input = in.readLine();
			int ip = Integer.parseInt(input);

			if (ip == 1) {
				menu();
			} else if (ip == 0)
				processExit();
			else {
				System.out.println("���ڸ� �Է����ּ���");

				System.out.println("��� �Ͻ÷��� 1, ���� �Ͻ÷��� 0�� �Է����ּ���.");
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
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
		System.exit(0);

	}

}
